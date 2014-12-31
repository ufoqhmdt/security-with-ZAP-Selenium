/**
 * 
 */
package com.security.mvc.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/** 
 * @author GuoJun  E-mail: guojun828@126.com 
 * @version 2014年12月12日 上午12:36:56 
 * 
 */
/**@Description: 加密算法
 *  @version Revision: V1.0 2014年12月12日 上午12:36:56
 *  @author GuoJun mailto: guojun828@126.com
 */
public class DES {

	private String password = "9588028820109132570743325311898426347857298773549468758875018579537757772163084478873699447306034466200616411960574122434059469100235892702736860872901247123456";
	public byte[] desCrypto(byte[] datasource) {              
        try{  
        SecureRandom random = new SecureRandom();  
        DESKeySpec desKey = new DESKeySpec(password.getBytes());  
        //创建一个密匙工厂，然后用它把DESKeySpec转换成  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
        SecretKey securekey = keyFactory.generateSecret(desKey);  
        //Cipher对象实际完成加密操作  
        Cipher cipher = Cipher.getInstance("DES");  
        //用密匙初始化Cipher对象  
        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);  
        //现在，获取数据并加密  
        //正式执行加密操作  
        return cipher.doFinal(datasource);  
        }catch(Throwable e){  
                e.printStackTrace();  
        }  
        return null;  
	}  
	public byte[] decrypt(byte[] src) throws Exception {  
        // DES算法要求有一个可信任的随机数源  
        SecureRandom random = new SecureRandom();  
        // 创建一个DESKeySpec对象  
        DESKeySpec desKey = new DESKeySpec(password.getBytes());  
        // 创建一个密匙工厂  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
        // 将DESKeySpec对象转换成SecretKey对象  
        SecretKey securekey = keyFactory.generateSecret(desKey);  
        // Cipher对象实际完成解密操作  
        Cipher cipher = Cipher.getInstance("DES");  
        // 用密匙初始化Cipher对象  
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);  
        // 真正开始解密操作  
        return cipher.doFinal(src);  
	}
}  

