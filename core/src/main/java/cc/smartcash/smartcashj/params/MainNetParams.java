/*
 * Copyright 2013 Google Inc.
 * Copyright 2015 Andreas Schildbach
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

package cc.smartcash.smartcashj.params;

import cc.smartcash.smartcashj.core.*;
import cc.smartcash.smartcashj.net.discovery.*;

import java.net.*;

import static com.google.common.base.Preconditions.*;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends AbstractBitcoinNetParams {
    public static final int MAINNET_MAJORITY_WINDOW = 1000;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 950;
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 750;

    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(0x1e0ffff0L);
        dumpedPrivateKeyHeader = 191;
        addressHeader = 63;
        p2shHeader = 18;
        segwitAddressHrp = "sc";
        port = 9678;
        packetMagic = 0x5ca1ab1eL;
        bip32HeaderP2PKHpub = 0x0488b21e; // The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderP2PKHpriv = 0x0488ade4; // The 4 byte header that serializes in base58 to "xprv"
        bip32HeaderP2WPKHpub = 0x04b24746; // The 4 byte header that serializes in base58 to "zpub".
        bip32HeaderP2WPKHpriv = 0x04b2430c; // The 4 byte header that serializes in base58 to "zprv"

        majorityEnforceBlockUpgrade = MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = MAINNET_MAJORITY_WINDOW;

        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setTime(1496467978L);
        genesisBlock.setNonce(245887);
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 210000;
        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("000007acc6970b812948d14ea5a0a13db0fdd07d5047c7e69101fa8b361e05a4"),
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        checkpoints.put(0, Keccak256Hash.wrap("000007acc6970b812948d14ea5a0a13db0fdd07d5047c7e69101fa8b361e05a4"));
        checkpoints.put(75000, Keccak256Hash.wrap("000000000002ee203026137ebc460e1886e09b9fdb0e83697e5a74976088e75c"));
        checkpoints.put(170000, Keccak256Hash.wrap("000000000000670ff41fbb4ad819b48bfe1c35623f13297d3fbf9bf02abcd87c"));
        checkpoints.put(500000, Keccak256Hash.wrap("00000000000016a1fa8e650e5a82babefeb9225ffe78614bc4b23cf160d16eeb"));
        checkpoints.put(1000000, Keccak256Hash.wrap("00000000000008e14776878dba228ac957a97205df4716ce1913ae4339e7aeb9"));
        checkpoints.put(1030000, Keccak256Hash.wrap("00000000000000d7e76cc6c30a2bece10f552123ad3c9a63beceb0d553a46f04"));

        dnsSeeds = new String[] {
                "seed.smrt.cash",
                "seed1.smrt.cash",
                "seed2.smrt.cash",
                "seed1.smartcash.org",
                "seed2.smartcash.org",
                "seed.smartcash.cc",
                "seed2.smartcash.cc",
                "seed3.smartcash.cc",
                "seed4.smartcash.cc",
        };
        httpSeeds = null;

        // These are in big-endian format, which is what the SeedPeers code expects.
        // Updated Apr. 11th 2019
        addrSeeds = new String[] {
                "seed.smrt.cash",
                "seed1.smrt.cash",
                "seed2.smrt.cash",
                "seed1.smartcash.org",
                "seed2.smartcash.org",
                "seed.smartcash.cc",
                "seed2.smartcash.cc",
                "seed3.smartcash.cc",
                "seed4.smartcash.cc"
        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
