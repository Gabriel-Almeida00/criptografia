package criptografia

import grails.gorm.transactions.Transactional
import org.springframework.security.crypto.password.PasswordEncoder

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@Transactional
class CriptografiaService implements PasswordEncoder {

    String SECRET_KEY = 'aPmZK6;91{3i&]2+'

    @Override
    String encode(CharSequence rawPassword) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            byte[] encryptedTextBytes = cipher.doFinal(rawPassword.toString().getBytes("UTF-8"))
            return Base64.getEncoder().encodeToString(encryptedTextBytes)
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar a senha", e)
        }
    }

     String decrypt(String encryptedPassword) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES")
            cipher.init(Cipher.DECRYPT_MODE, secretKey)
            byte[] decryptedTextBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword))
            return new String(decryptedTextBytes)
        } catch (Exception e) {
            throw new RuntimeException("Erro ao descriptografar a senha", e)
        }
    }

    @Override
    boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false
    }

    @Override
    boolean upgradeEncoding(String encodedPassword) {
        return super.upgradeEncoding(encodedPassword)
    }
}
