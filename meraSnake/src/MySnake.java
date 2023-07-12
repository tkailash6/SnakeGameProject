import javax.swing.*;
import java.awt.*;

public class MySnake{

public static void main(String[]args) {

        JFrame frame =new JFrame("myGame");// eska use frame bnane ke liye kia hai
        frame.setBounds(10,10,905,700);// yhaan pe height or width wgreah di hai frame ki
        frame.setResizable(false);// esko krne se usser apne aap frame ka size nhi ched paayega
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// es line ko search maariyo kailash chatgpt pe
        // or jo line mrko samjh nhi aayegi uske aage comment mai mai (chatgpt) likh dunga> taaki usko baad
        // mai serach krke samjh paau

        GamePanel panel=new GamePanel();// ye mene gamepanel ka object bnaya hai. gamepanel ko add krne ke liye mysnake class ke andar
        frame.add(panel);// vese to ye panel ko frame ke andar daalne ke liye use kiya hai pr esko fhir bhi dekhna pdega chatgpt ke uppar
        //add mai sirf compnent daal skte hai
        //or panel ek component nhi hai. pr JPanel ek component hai or jb Gamepanel ne usko extend kiya to
        // gamepanel bhi component bann gya or panel gamepanel ka hie refrence hai
        frame.setVisible(true);// harr frame invisible hota hai esliye uss ko visible krne ke liye  es line ko likhna pda
         panel.setBackground(Color.DARK_GRAY);// ye panel ka colour set krega



    }
}