package dk.easv.easvticket.be.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Encrypter {


    public static String hashPassword(String password) {
        int workFactor = 12;
        String bcryptHashString = BCrypt.withDefaults().hashToString(workFactor, password.toCharArray());
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        return bcryptHashString;
    }
}
