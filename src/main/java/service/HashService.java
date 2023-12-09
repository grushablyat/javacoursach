package service;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class HashService {
    public String hash(String toBeHashed) {
        return Hashing.sha256().hashString(toBeHashed, StandardCharsets.UTF_8).toString();
    }
}
