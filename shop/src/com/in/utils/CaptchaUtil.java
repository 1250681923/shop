
package com.in.utils;
 
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.List;
 
public class CaptchaUtil {
 
    public static String [] fuhao = {"+","*"};
    public static int num = 0;
 
    //Générer une formule
    public static String random(){
 
        List list = new ArrayList();
        Random ran = new Random();
        int n1 = ran.nextInt(10);
        int n2 = ran.nextInt(10);
        String f = fuhao[ran.nextInt(fuhao.length)];
        StringBuilder sb = new StringBuilder(4);
 
        switch (f){
            case "+":
                num = n1+n2;
                break;
            case "*":
                num = n1*n2;
                break;
        }
        sb.append(n1);
        sb.append(f);
        sb.append(n2);
        sb.append("=");
 
        return sb.toString();
    }
    public static void outputImage(String str, OutputStream os){
        Random ran = new Random();
        //Créer un objet image
        BufferedImage img = new BufferedImage(100,40, BufferedImage.TYPE_INT_RGB);
        //Obtenir une toile
        Graphics g = img.getGraphics();
        //Définir l'arrière-plan
        g.setColor(Color.white);
        g.fillRect(0,0,100,40);
        //Ecrire
        g.setColor(Color.black);
        g.setFont(new Font("微软雅黑",Font.PLAIN,30));
        g.drawString(str,10,40);
        //Génère aléatoirement trois lignes
        g.drawLine(ran.nextInt(100),ran.nextInt(40),
                ran.nextInt(100),ran.nextInt(40));
        g.drawLine(ran.nextInt(100),ran.nextInt(40),
                ran.nextInt(100),ran.nextInt(40));
        g.drawLine(ran.nextInt(100),ran.nextInt(40),
                ran.nextInt(100),ran.nextInt(40));
        // Sortie
        // Formats de compression courants: jpeg (compression avec perte), png (compression sans perte)...
        try {
            ImageIO.write(img, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
 
}
