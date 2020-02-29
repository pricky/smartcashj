/*
 * Copyright 2011 Google Inc.
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

package cc.smartcash.smartcashj.core;

import java.util.Objects;

public class InventoryItem {

    /**
     * 4 byte uint32 type field + 32 byte hash
     */
    static final int MESSAGE_LENGTH = 36;

    public enum Type {
        ERROR(0x0), TRANSACTION(0x1), BLOCK(0x2),
        // BIP37 extension:
        FILTERED_BLOCK(0x3),
        // BIP44 extensions:
        WITNESS_TRANSACTION(0x40000001), WITNESS_BLOCK(0x40000002), WITNESS_FILTERED_BLOCK(0x40000003),
        MSG_TXLOCK_REQUEST(0x5),
        MSG_TXLOCK_VOTE(0x6),
        MSG_SPORK(0x7),
        MSG_SMARTNODE_PAYMENT_VOTE(0x8),
        MSG_SMARTNODE_PAYMENT_BLOCK(0x9), // reusing, was MSG_SMARTNODE_SCANNING_ERROR previousely, was NOT used in 12.0
        MSG_BUDGET_VOTE(0x9), // depreciated since 12.1
        MSG_BUDGET_PROPOSAL(0x10), // depreciated since 12.1
        MSG_BUDGET_FINALIZED(0x11), // depreciated since 12.1
        MSG_BUDGET_FINALIZED_VOTE(0x12), // depreciated since 12.1
        MSG_SMARTNODE_QUORUM(0x13), // not implemented
        MSG_SMARTNODE_ANNOUNCE(0x14),
        MSG_SMARTNODE_PING(0x15),
        MSG_DSTX(0x16),
        MSG_GOVERNANCE_OBJECT(0x17),
        MSG_GOVERNANCE_OBJECT_VOTE(0x18),
        MSG_SMARTNODE_VERIFY(0x19)
        ;

        public final int code;

        private Type(int code) {
            this.code = code;
        }

        public static Type ofCode(int code) {
            for (Type type : values())
                if (type.code == code)
                    return type;
            return null;
        }
    }

    public final Type type;
    public final Sha256Hash hash;
    public final Keccak256Hash hashKeccak;

    public InventoryItem(Type type, Sha256Hash hash, Keccak256Hash hashKeccak) {
        this.type = type;
        this.hash = hash;
        this.hashKeccak = hashKeccak;
    }

    @Override
    public String toString() {
        return type + ": " + hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItem other = (InventoryItem) o;
        return type == other.type && (hash.equals(other.hash) || hashKeccak.equals(other.hashKeccak));
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, hash);
    }
}
