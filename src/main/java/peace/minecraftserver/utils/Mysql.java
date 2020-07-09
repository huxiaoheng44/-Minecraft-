package peace.minecraftserver.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.omg.CORBA.INTERNAL;
import peace.minecraftserver.MinecraftServer;

public class Mysql {
    private Connection connection;


    private String host = MinecraftServer.config.getString("mysql-host");

    private String port = MinecraftServer.config.getString("mysql-port");

    private String database = MinecraftServer.config.getString("mysql-database");

    private String username = MinecraftServer.config.getString("mysql-username");

    private String password = MinecraftServer.config.getString("mysql-password");

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return this.database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void connect() {
        if (!isConnected())
            try {
                this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true"+"&useSSL=false", this.username, this.password);
                ((MinecraftServer)MinecraftServer.getPlugin(MinecraftServer.class)).getServer().getConsoleSender().sendMessage("connected to database!");
            } catch (SQLException e) {
                e.printStackTrace();
                ((MinecraftServer)MinecraftServer.getPlugin(MinecraftServer.class)).getServer().getConsoleSender().sendMessage("not connect to database! :"+e.toString());
            }
    }

    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String qry) {
        try {
            PreparedStatement ps = this.connection.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(){
        //在线时长表
        update("CREATE TABLE IF NOT EXISTS `playtime` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "\t`UUID` VARCHAR(100) COMMENT '用户id',\n" +
                "\t`Name` VARCHAR(100) COMMENT '用户名',\n" +
                "\t`Seconds` INT(100) COMMENT '总的在线时间',\n" +
                "\t`LastdaySeconds` INT(100) default 0 COMMENT '历史在线时间',\n" +
                "\t PRIMARY KEY (`id`) USING BTREE\n" +
                ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;");

        //保险表
        update("CREATE TABLE IF NOT EXISTS `guarantee`  (\n" +
                "  `orderid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'orderID',\n" +
                "\t`UUID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',\n" +
                "\t`gid` VARCHAR(12) NOT NULL DEFAULT 0 COMMENT '保险编号',\n" +
                "  `expires` int(100) NOT NULL DEFAULT 0 COMMENT '过期时间',\n" +
                "  PRIMARY KEY (`orderid`) USING BTREE\n" +
                ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;");

        //签到表
        update("CREATE TABLE IF NOT EXISTS `checkin`  (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "\t`UUID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',\n" +
                "\t`lastdate` DATE  COMMENT '上次登录日期',\n" +
                "  `conday` int(100) NOT NULL DEFAULT 0 COMMENT '连续登录天数',\n" +
                "  PRIMARY KEY (`id`) USING BTREE\n" +
                ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;");
    }

    /*
    保险表
     */

    public void setGuarantee(OfflinePlayer p, String gid,int expires){
        if (!isUserExistInGuarantee(p.getUniqueId().toString()))
            try {
                PreparedStatement ps = this.connection.prepareStatement("INSERT INTO guarantee (UUID,gid,expires) VALUES (?,?,?)");
                ps.setString(1, p.getUniqueId().toString());
                ps.setString(2, gid);
                ps.setInt(3, expires);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        else {
            try {
                PreparedStatement ps = this.connection.prepareStatement("UPDATE guarantee SET expires = ? WHERE UUID = ? and gid = ?");
                ps.setInt(1, expires);
                ps.setString(2, p.getUniqueId().toString());
                ps.setString(3, gid);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getGuarantee(OfflinePlayer p,String gid){
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT expires FROM guarantee WHERE UUID = ? and gid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ps.setString(2, gid);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return Integer.valueOf(rs.getInt("expires"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(-1);
    }

    public boolean isUserExistInGuarantee(String uuid) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT gid FROM guarantee WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    签到表
     */

    public void setCheckIN(OfflinePlayer p,int conday){
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        if (!isUserExistInCheckIN(p.getUniqueId().toString()))
            try {
                PreparedStatement ps = this.connection.prepareStatement("INSERT INTO checkin (UUID,lastdate,conday) VALUES (?,?,?)");
                ps.setString(1, p.getUniqueId().toString());
                ps.setDate(2, sqlDate);
                ps.setInt(3, conday);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        else {
            try {
                PreparedStatement ps = this.connection.prepareStatement("UPDATE checkin SET lastdate = ?,conday = ? WHERE UUID = ?");
                ps.setDate(1, sqlDate);
                ps.setInt(2, conday);
                ps.setString(3, p.getUniqueId().toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int checkin(OfflinePlayer p){
        ResultSet rs = getCheckIN(p);
        if(rs!=null){
            try{
                java.sql.Date data = rs.getDate("lastdate");
                int conday = rs.getInt("conday");

                java.util.Date utilDate = new java.util.Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(utilDate);
                java.sql.Date lastday1 = new java.sql.Date(cal.getTime().getTime());
                cal.add(Calendar.DATE, -1);
                java.sql.Date lastday2 = new java.sql.Date(cal.getTime().getTime());

                //签到日期为前一天算连续签到
                if(data.toString().equals(lastday2.toString())){
                    setCheckIN(p,conday+1);
                    VaultUtil.give(p.getUniqueId(),(conday+1)*10);
                    ((Player)p).sendMessage("连续签到"+(conday+1)+"天");
                    ((Player)p).sendMessage("获得金币奖励:"+Integer.toString((conday+1)*10));
                    return conday+1;
                //签到日期为今天不算签到
                }else if(data.toString().equals(lastday1.toString())){
                    return conday;
                }else{
                    setCheckIN(p,1);
                    VaultUtil.give(p.getUniqueId(),10);
                    ((Player)p).sendMessage("连续签到"+1+"天");
                    ((Player)p).sendMessage("获得金币奖励:"+Integer.toString(10));
                    return 1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            setCheckIN(p,1);
            VaultUtil.give(p.getUniqueId(),10);
            ((Player)p).sendMessage("连续签到"+1+"天");
            ((Player)p).sendMessage("获得金币奖励:"+Integer.toString(10));
        }
        return 1;
    }

    public ResultSet getCheckIN(OfflinePlayer p){
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT lastdate,conday FROM checkin WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUserExistInCheckIN(String uuid) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT lastdate,conday FROM checkin WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /*
    在线时间表
     */



    public void setTime(OfflinePlayer p, int second) {
        if (!isUserExist(p.getUniqueId().toString()))
            try {
                PreparedStatement ps = this.connection.prepareStatement("INSERT INTO playtime (UUID,Name,Seconds) VALUES (?,?,?)");
                ps.setString(1, p.getUniqueId().toString());
                ps.setString(2, p.getName());
                ps.setInt(3, 0);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        else {
            try {
                PreparedStatement ps = this.connection.prepareStatement("UPDATE playtime SET Name = ?, Seconds = ? WHERE UUID = ?");
                ps.setString(1, p.getName());
                ps.setInt(2, second);
                ps.setString(3, p.getUniqueId().toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getSeconds(String uuid) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT Seconds FROM playtime WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return Integer.valueOf(rs.getInt("Seconds"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(-1);
    }

    public Integer TodaydaySeconds(String uuid) {
        Integer now,lastday;
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT Seconds,LastdaySeconds FROM playtime WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                now = Integer.valueOf(rs.getInt("Seconds"));
                lastday = Integer.valueOf(rs.getInt("LastdaySeconds"));
                return now-lastday;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(-1);
    }

    public List<String> getUsers() {
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM playtime");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                list.add((String)rs.getObject("uuid"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isUserExist(String uuid) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT Name FROM playtime WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isConnected() {
        return !(this.connection == null);
    }

    public boolean isEnabled() {
        return MinecraftServer.config.getBoolean("mysql");
    }
}

