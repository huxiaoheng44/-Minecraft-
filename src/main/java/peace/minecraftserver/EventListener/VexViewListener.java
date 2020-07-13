package peace.minecraftserver.EventListener;

import lk.vexview.api.VexViewAPI;
import lk.vexview.event.ButtonClickEvent;
import lk.vexview.event.KeyBoardPressEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.VexView.InsureGui;
import peace.minecraftserver.VexView.MainGui;
import peace.minecraftserver.utils.InsureUtils;

import java.util.ArrayList;
import java.util.List;

public class VexViewListener implements Listener {

    InsureUtils insureUtils = new InsureUtils();
//    @EventHandler
//    public void PlayerOpneGui(VexGuiOpenEvent event){
//        VexGui gui = event.getGui();
//        //如果是购买保险
//        if(gui.getUrl().equals("[local]insurePurchase.png")){
//            List<String> messages = new ArrayList<String>();
//            //购买第一个购买按钮
//            VexButton b1 = gui.getButtonByHashCode(1);
//            messages.add("确认要购买钻石保险吗");
//            VexViewAPI.openGui(event.getPlayer(),MyGUI.AffirmLogue(event.getPlayer(), (ArrayList) messages));
//            //MinecraftServer.plugin.getLogger().info("点击了按钮一");
//            //第二个购买按钮
//            VexButton b2 = gui.getButtonByHashCode(2);
//            //第三个购买按钮
//            VexButton b3 = gui.getButtonByHashCode(3);
//            //VexViewAPI.openGui(MyGUI.AffirmLogue(event.getPlayer(), (ArrayList) messages);
//        }
//    }
    @EventHandler
    public void openMain(KeyBoardPressEvent event){
        //MinecraftServer.plugin.getLogger().info(String.valueOf(event.getKey()));
        //点击p打开主界面
        if(event.getKey()==25){
            VexViewAPI.openGui(event.getPlayer(),MainGui.main(event.getPlayer()));
        }
    }

    @EventHandler
    public void PlayClickButton(ButtonClickEvent event){
        Player player = event.getPlayer();
        String id = (String) event.getButtonID();
        List<String> messages = new ArrayList<String>();
        //第一个购买按钮
        if(id.equalsIgnoreCase("buy1")){
            insureUtils.could_buy(player,100,"diamond",3600);
        }
        //第二个购买按钮
        else if(id.equalsIgnoreCase("buy2")){
            insureUtils.could_buy(player,50,"gold",3600);
        }
        //第三个购买按钮
        else if(id.equalsIgnoreCase("buy3")){
            insureUtils.could_buy(player,100,"monster_kill",3600);
        }
        //确认按钮
        else if(id.equalsIgnoreCase("affirm")){

            //用完记得remove掉
        }
        //取消确认
        else if(id.equalsIgnoreCase("cancel")){

        }
    }
}
