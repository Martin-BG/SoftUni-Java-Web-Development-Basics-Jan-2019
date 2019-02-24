package org.softuni.exam.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.kosprov.jargon2.api.Jargon2.*;

/**
 * https://github.com/kosprov/jargon2-api
 */
@ApplicationScoped
public class PasswordHasher {

    private static final Logger LOG = Logger.getLogger(PasswordHasher.class.getName());

    private static final int MEMORY_COST = 65536;
    private static final int TIME_COST = 3;
    private static final int LANES = 4;
    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 16;

    private Hasher hasher;
    private Verifier verifier;
    private ByteArray secret;

    public PasswordHasher() {
        // Empty constructor and comment into it to please SonarLint's complains on not initialized private fields :)
    }

    @PostConstruct
    private void init() {
        int threads = Runtime.getRuntime().availableProcessors() - 1;
        secret = toByteArray("3c06acd4b68c4ba1b55dd90269b8ac30a9fc7c607ac614e01a9d51b7895309ac");
        hasher = jargon2Hasher()
                .secret(secret)
                .type(Type.ARGON2d)
                .memoryCost(MEMORY_COST)
                .timeCost(TIME_COST)
                .parallelism(LANES, threads)
                .saltLength(SALT_LENGTH)
                .hashLength(HASH_LENGTH);
        verifier = jargon2Verifier()
                .secret(secret)
                .threads(threads);
    }

    @PreDestroy
    private void destroy() {
        secret.clear();
    }

    public String encodedHash(char[] password) {
        try (ByteArray passwordByteArray = toByteArray(password).clearSource()) {
            return hasher.password(passwordByteArray).encodedHash();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error during password hashing", e);
            return null;
        }
    }

    public boolean verifyEncoded(String encodedHash, char[] password) {
        try (ByteArray passwordByteArray = toByteArray(password).clearSource()) {
            return verifier.hash(encodedHash).password(passwordByteArray).verifyEncoded();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error during password verification", e);
            return false;
        }
    }

    public boolean isUpdated(String encodedHash) {
        return hasher.propertiesMatch(encodedHash);
    }
}
