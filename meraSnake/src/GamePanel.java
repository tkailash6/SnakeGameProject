// es snake ko move krane ke liye gamepanel/frame(ye naam YT waale ne bola hai pr mrko chatgpt pe dekhna pdega ki frame ya fhir gamepanel kya baar baar draw hoga) ko baar baar draw krna pdega or  bilkul saath mai hie snake ki position ko change krte
// hue chalna pdega. esse ek illution create hoga jisse lgega ki snake move kr rha hai. har 100 milisecond ke baad (gamepanel/frame) baar baar draw hoga




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
      private int [] snakexlength= new int[750];// ye snake ki body ki position ko store krane ke liye 2 array ki
   // jrrut hogi
      private int [] snakeylength=new int[750];
      private int  lengthOfSnake=3;// esmai snake ki length rhegi. or initial stage mai ye 3 rhegi

     private int[] xPos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
     private int[] yPos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
      // ye xpos or ypos array saanp ke enemy ke  spawn point hai

    private Random random=new Random();// esko YT waala bola ki random class chahiye humko. or baaki enemy ko randomly spawn bhi to krna hai
    private int enemyX,enemyY;// ye bhi kuch enemy position se related hai. pr emko exactly nhi pta. free time mai dekhunga eski working ya use kya hai
    // YT wala bola es enemyx or enemyy ko set krna hoga or fhir vo gamepanel consturctor mai gya


       private boolean left=false;// ye 4ro variables snake ke direction ko represent krenge
       private boolean right=true;// right initially true rhega. kyunki saanp ka muh starting mai right direction mai hie hoga
       private boolean up=false;// or ek baat hor saanp ka head ek time mai ek hie direction ki taraf ho skta hai
      // eska mtlb ek time mai 4ro mai se ek hie true hoga
       private boolean down=false;
       private int moves=0;// ye initial stage check krne ke liye hai. agar moves 0 hai to ye initial stage hai
       private int score=0;// ye score hai saanp ka
       private boolean gameOver=false;// ye game over ke liye hai
      private ImageIcon snaketitle= new ImageIcon(getClass().getResource("snaketitle.jpg"));// ye private image ka ek object hai.YT waale ne yhi kaha hai
      // ye snaketitle ko daalne ke liye use kiya hai
      private ImageIcon leftmouth= new ImageIcon(getClass().getResource("leftmouth.png"));
    private ImageIcon rightmouth= new ImageIcon(getClass().getResource("rightmouth.png"));
    private ImageIcon upmouth= new ImageIcon(getClass().getResource("upmouth.png"));
    private ImageIcon downmouth= new ImageIcon(getClass().getResource("downmouth.png"));
    private ImageIcon snakeimage= new ImageIcon(getClass().getResource("snakeimage.png"));

    private ImageIcon enemy= new ImageIcon(getClass().getResource("enemy.png"));


    private Timer timer;// ye Timer ek inbuilt class hai.or ye gamepanel/frame ko baar baar draw krne mai lgegi
    private int delay=100;// ye variabe bhi gamepanel/frame ko baar baar draw krne ke liye lgega


      GamePanel(){//ye gamepanel ka constuctor hai.
          addKeyListener(this);// ye unn 4ro button waale if ko challane ke liye lgaaya hai. or mene eske baare mai unn if ke last mai bhi likha hai
          setFocusable(true);// 2 method orr bhi call krne pdenge taaki ye keylistner j panel pr work kre
          setFocusTraversalKeysEnabled(true);// or ye hai vo dusra method
           timer =new Timer(delay, this);// esmai this ka mtlb action listner ka object hai.kyunki humnse same class mai hie impliment kiya hai
           timer.start();// ye timer class ko start kr dega
          // YT waala bol rha hai ki ye timer class har 100 milisecondke baad action listner ke object(this) ko call krega or usse actionperformed method har 100 milisecond baad call hoga

          // YT waala bola (enemy ki position ko set krne ke liye ek new method bna leta hu)
          // YT waala phir se bola ki (jb bhi hume enemy ki position set krni hogi to mai ess method ko call krunga)
          newEnemy();


      }



    @Override// ye niche graphics ko YT wala bol rha hai ki graphics class ka object.chatgpt pe dekhna pdega
      public void paint(Graphics g){// ye YT waale kaha ki har j component ka pass ek paint method hota hai. or ye vhi hai.ye inbuilt hai
         super.paint(g);// ye pta nhi kiske pass tha jpanel ya fhir gamepanel. chatgpt pe dekhna pdega

         g.setColor(Color.white);//ye boarder deaw krne ke liye hai
         g.drawRect(24,10,851,55);// ye mera uppar wala rectangle ke dimensions hai
         g.drawRect(24,74,851,576);// ye niche waale dabbe ke dimensions hai or esmai colour
         // se krne ki jrrurt nhi kyunki mene white kr diya tha uppar, esliye dono boarders ki outline white colour mai hogi

         snaketitle.paintIcon(this,g,25, 11);// eske anadar humko component pass krna hai, or ye puch rha hai ki humko kiss component pe icon draw krna hai
         // esliye humne this yaani ki jpanel or graphics ka object g bhi pass kr diya
         g.setColor(Color.BLACK);// ye mene nichle dabbe ka colou black set kiya hai
         g.fillRect(25,75,850,575);// ye nichle dabbe ka area hai. or uppar waale perimeter  uski outline thie

           if(moves==0){// agar moves 0 hai to snake initial stage pr hai
               snakexlength[0]=100;// 0 pe humara head rhega
               snakexlength[1]=75;// 1 pe snake ka dusra bindu
               //or ye 100,75,50 ye kuch pixal ka hisab hai
               snakexlength[2]=50;//2 pe 3rd bindu rhega snake ka

               snakeylength[0]=100;// 0 pe humara head rhega
               snakeylength[1]=100;// 1 pe snake ka dusra bindu
               //or ye 100,100,100 ye kuch pixal ka hisab hai
               snakeylength[2]=100;//2 pe 3rd bindu rhega snake ka
              // moves++;// uppar snake intialize ho chuka hai ab moves ko ++ krna pdega
               // ye waale move ki vajjah se snake ek dum starting se hie bhagne lgta hai pr mrko usko starting mai pause rkhna hai or key dbaane pe shuru krna hai
               // esliye mai move ko niche shift kr ra hu
           }
           if(left){// ye esliye hai agar saanp left ki taraf hoga to usko left head waali image deni hai
               leftmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
           }
          if(right){// ye esliye hai agar saanp right ki taraf hoga to usko right head waali image deni hai
              rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
          }
          if(up){// ye esliye hai agar saanp up ki taraf hoga to usko up head waali image deni hai
              upmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
          }
          if(down){// ye esliye hai agar saanp down ki taraf hoga to usko down head waali image deni hai
              downmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
          }
          // ye inn saare if ki vjaah se saanp ka head draw ho chuka hai
          for(int i=1;i<lengthOfSnake;i++){// ye loop saanp ki body ko draw krne ke liye hai.or [0] position pe head
              // draw ho chuka hai esliye loop 1 se shuru ki
              snakeimage.paintIcon(this,g,snakexlength[i],snakeylength[i]);

          }
          enemy.paintIcon(this,g,enemyX,enemyY);// ye enemy ko draw krega
           if(gameOver){// game over,press space to restart ko draw krne ke liye hai ye
               g.setColor(Color.WHITE);//chatgpt
               g.setFont(new Font("Arial",Font.BOLD,50));// chatgpt
               g.drawString("Game Over",300,300);//chatgpt
                // ye nichli 2 lines press space to restart ke liye hai
               g.setFont(new Font("Arial",Font.PLAIN,20));//chatgpt
               g.drawString("Press SPACE to Restart",320,350);//chatgpt


           }
           // ye nichli 4 lines score or lengthofsnake draw or dikhane ke liye hai
           g.setColor(Color.WHITE);//chatgpt
           g.setFont(new Font("Arial",Font.PLAIN,14));// chatgpt
           g.drawString("Score : "+score,750,30);//chatgpt
           g.drawString("Length : "+lengthOfSnake,750,50);//chatgpt


        g.dispose(); // ye uppar poora paint(class hai ya method pta nhi) usko khatam kr dega

      }

    @Override
    public void actionPerformed(ActionEvent e) {// YT wala bola ki ye method Action listner ki vjah se aaya hai
    // es method ke andar hum snake ki head ki position ko change krenge

        for(int i=lengthOfSnake-1;i>0;i--){// ye loop head ki position ko uske pichle body part ko degi.or uska pichla body part usse pichle ko apni position dega
            // or esko body ke last part se shuru krna hai. mtlb ye hai ki body ka last part usse agle ki previous position lega
            snakexlength[i]=snakexlength[i-1];
            snakeylength[i]=snakeylength[i-1];

        }
        if(left){// head ko left move krne ke liye 25 pixel kum krne pdenge x mai se
         snakexlength[0]=snakexlength[0]-25;
        }
        if(right){// head ko right move krne ke liye 25 pixel plus krne pdenge x mai
            snakexlength[0]=snakexlength[0]+25;
        }
        if(up){// head ko up move krne ke liye 25 pixel kum krne pdenge y mai se
            snakeylength[0]=snakeylength[0]-25;
        }
        if(down){// head ko down move krne ke liye 25 pixel plus krne pdenge y mai
            snakeylength[0]=snakeylength[0]+25;
        }
        if(snakexlength[0]>850){// es conditon  mai saanp jese hie right boarder touch krega to vo left se niklega
            snakexlength[0]=25;
        }
        if(snakexlength[0]<25){// es conditon  mai saanp jese hie left boarder touch krega to vo right se niklega
            snakexlength[0]=850;
        }
        if(snakeylength[0]>625){// es conditon  mai saanp jese hie (up/down. abhi to mrko pta ni ki ye up ka hai ya down ka) boarder touch krega to vo (up/down) se niklega
            snakeylength[0]=75;
        }
        if(snakeylength[0]<75){// es conditon  mai saanp jese hie (up/down. abhi to mrko pta ni ki ye up ka hai ya down ka) boarder touch krega to vo (up/down) se niklega
            snakeylength[0]=625;
        }


          collidesWithEnemy();// ye method call ka use. jb saanp, enemy se collide krega uss ke liye hai
          collidesWithBody();// ye method call ka use. jb saanp, apni hie body se takraayega

        //YT waala bola ab es method ko repaint krna pdega
        repaint(); // ye paint method ko baar baar call krega or dispose method usko baar baar dispose krega

    }
 // ye 3no methods snake ko button dba ke move kraayenge

    @Override
    public void keyPressed(KeyEvent e) {// or koi key press ki gyi hai to uski information keyEvent ke object mai hogi
          //ye method akela hie mrko 4 keys se control krne dega snake ko. or 5TH key SPACE restart ke liye hai
        if(e.getKeyCode()==KeyEvent.VK_SPACE){// ye space key dekhega. agar space key press hogi to ....
            restart();// agar space key dbegi to restart krna hai
        }



          if(e.getKeyCode()==KeyEvent.VK_LEFT && (!right)){ // es line ko chatgpt pe dekhna pdega. pr jo esmai condition hai !right waali eska mtlb hai ki left key press hai to saaanp right nhi mudna chahiye
              //kyunki saanp 3no direction mai hie turn le skta hai, pr bilul opposite nhi

              left=true; // esmai agar left key dbi hogi to saanp ko left ko le jaayega ye
              right=false;
              up=false;
              down=false;
              moves++;//mene move ko uppar se niche shift kr diya

          }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left)){ // es line ko chatgpt pe dekhna pdega. pr jo esmai condition hai !left waali eska mtlb hai ki right key press hai to saaanp left nhi mudna chahiye

            left=false;
            right=true;// esmai agar right key dbi hogi to saanp ko right ko le jaayega ye
            up=false;
            down=false;
            moves++;//mene move ko uppar se niche shift kr diya

        }
        if(e.getKeyCode()==KeyEvent.VK_UP && (!down)){ // es line ko chatgpt pe dekhna pdega. pr jo esmai condition hai !down waali eska mtlb hai ki up key press hai to saaanp down nhi mudna chahiye

            left=false;
            right=false;
            up=true; // esmai agar up key dbi hogi to saanp ko up ko le jaayega ye
            down=false;
            moves++;//mene move ko uppar se niche shift kr diya

        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN &&(!up)){ // es line ko chatgpt pe dekhna pdega. pr jo esmai condition hai !up waali eska mtlb hai ki down key press hai to saaanp up nhi mudna chahiye

            left=false;
            right=false;
            up=false;
            down=true;// esmai agar down key dbi hogi to saanp ko down ko le jaayega ye
            // es chaaro if ko challne ke liye mrko keylistner ko add krna pdega gamepanel constructor mai
            moves++;//mene move ko uppar se niche shift kr diya
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {// ye 3no methods keylistner se aaye hai. key listner class hai ya nhi abhi to mrko pta nhi

    }
    private void newEnemy() {// es method ke andar hum enemy ki position ko set krenge
          enemyX=xPos[random.nextInt(34)];// xpos array mai total 34 elements hai or ye mrko koi si bhi random position de dega mrko enemy ki
          enemyY=yPos[random.nextInt(23)];// ypos array mai total 23 elements hai or ye mrko koi si bhi random position de dega mrko enemy ki
           for(int i=lengthOfSnake-1;i>=0;i--){// ye for loop esliye hai kyuki hum nhi chahte ki enemy snake ki body ke uppar ya andar hie spawm ho jaaye
               if(snakexlength[i]==enemyX && snakeylength[i]== enemyY){// esmai hum ye dekh rhe hai ki snake ke kisi bhi part ki position, enemy ki position ke braber ho jaaye to, mtlb ye hai ki enemy saanp ki body ke uppar hie show hoga, or hume esko rokna hai
                   newEnemy();// ye method dobara call hoga or enemy ki position badal jeeyegi. agar uppar waali condition true hui to, or enemy saanp ke uppar spawn nhi hoga


               }

           }

    }
    private void collidesWithEnemy(){
          if(snakexlength[0]==enemyX && snakeylength[0]== enemyY){
              newEnemy();// esmai hum check krenge ki enemy, saanp se collide kr rha hai to use enenmy ko eat kr liya hai or ab enemy ko new position deni pdegi
              lengthOfSnake++;// collision hote hie saanp ki length 1 se bdh jaayegi esliye ++ kiya
              score++;// score bhi ++ hoga


          }

    }
    private void collidesWithBody(){//ye check krne ke liye ki saanp jb apni body se takraaye
          for(int i=lengthOfSnake-1;i>0;i--){
              if(snakexlength[i]==snakexlength[0] && snakeylength[i]==snakeylength[0] ){// ye check krega ki kisi bhi body part ki position head ke brabar hai, or agar hai to saanp body se takra gya hai
                  timer.stop();// saanp ke body se takraate hie timer ko stop kr dena hai
                  gameOver=true;// or saath mai esko true kr dena hai

              }

          }

    }
    private void restart(){// ye restart hai kuch.(free time mai dekhunga esko kya kya scene hai eska)
          gameOver=false;// gameover ko false kr dunga mai
          moves=0;// moves ko 0 kr dunga
          score=0;// score ko reset kr dunga
          lengthOfSnake=3;// saanp ki length reset kr dunga
          left=false;
          right=true;//or saanp right faced krke initial position  pe laa dunga
          up=false;
          down=false;
          timer.start();
          //repaint();// ye repaint esliye kyunki saanp ki death ke baad space dba ke saanp or enemy to reset ho rhe thie pr gameover ka message nhi jaa rha tha
        // ussi gameover message ko space dbaane ke baad gayab krne ke liye repaint(); kiya
        // ye to gdbad hai re baba mera to bina repaint ke bhi thik chal ra hai

    }

}
