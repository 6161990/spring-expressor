package com.company.design;

import com.company.design.facade.Ftp;
import com.company.design.facade.Reader;
import com.company.design.facade.SftpClient;
import com.company.design.facade.Writer;
import com.company.design.strategy.*;

public class Main {

   public static void main(String[] args) {

       Encoder encoder = new Encoder();

       //base64
       EncodingStrategy base64 = new Base64Strategy();

       //normal
       EncodingStrategy normal = new NormalStrategy();

       String message = "hello java";

       encoder.setEncodingStrategy(base64);
       String base64Result = encoder.getMessage(message);
       System.out.println(base64Result);

       encoder.setEncodingStrategy(normal);
       String normalResult = encoder.getMessage(message);
       System.out.println(normalResult);

       encoder.setEncodingStrategy(new AppendStrategy());
       System.out.println(encoder.getMessage(message));

   }

}
