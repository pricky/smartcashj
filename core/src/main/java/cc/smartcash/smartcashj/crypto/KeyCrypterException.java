/*
 * Copyright by the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.smartcash.smartcashj.crypto;

/**
 * <p>Exception to provide the following:</p>
 * <ul>
 * <li>Provision of encryption / decryption exception</li>
 * </ul>
 * <p>This base exception acts as a general failure mode not attributable to a specific cause (other than
 * that reported in the exception message). Since this is in English, it may not be worth reporting directly
 * to the user other than as part of a "general failure to parse" response.</p>
 */
/**
 * <p>Exception to provide the following:</p>
 * <ul>
 * <li>Provision of encryption / decryption exception</li>
 * </ul>
 * <p>This base exception acts as a general failure mode not attributable to a specific cause (other than
 * that reported in the exception message). Since this is in English, it may not be worth reporting directly
 * to the user other than as part of a "general failure to parse" response.</p>
 */
public class KeyCrypterException extends RuntimeException {
    private static final long serialVersionUID = -4441989608332681377L;

    public KeyCrypterException(String message) {
        super(message);
    }

    public KeyCrypterException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * This exception is thrown when a private key or seed is decrypted, it doesn't match its public key any
     * more. This likely means the wrong decryption key has been used.
     */
    public static class PublicPrivateMismatch extends KeyCrypterException {
        public PublicPrivateMismatch(String message) {
            super(message);
        }

        public PublicPrivateMismatch(String message, Throwable throwable) {
            super(message, throwable);
        }
    }

    /**
     * This exception is thrown when a private key or seed is decrypted, the decrypted message is damaged
     * (e.g. the padding is damaged). This likely means the wrong decryption key has been used.
     */
    public static class InvalidCipherText extends KeyCrypterException {
        public InvalidCipherText(String message) {
            super(message);
        }

        public InvalidCipherText(String message, Throwable throwable) {
            super(message, throwable);
        }
    }
}
