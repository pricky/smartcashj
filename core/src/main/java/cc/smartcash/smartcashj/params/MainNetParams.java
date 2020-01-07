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
        maxTarget = Utils.decodeCompactBits(0x1d00ffffL);
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
        httpSeeds = new HttpDiscovery.Details[] {
                // Andreas Schildbach
                new HttpDiscovery.Details(
                        ECKey.fromPublicOnly(Utils.HEX.decode("0238746c59d46d5408bf8b1d0af5740fe1a6e1703fcb56b2953f0b965c740d256f")),
                        URI.create("http://httpseed.bitcoin.schildbach.de/peers")
                )
        };

        // These are in big-endian format, which is what the SeedPeers code expects.
        // Updated Apr. 11th 2019
        addrSeeds = new int[] {
                // seed.bitcoin.sipa.be
                0x117c7e18, 0x12641955, 0x1870652e, 0x1dfec3b9, 0x4a330834, 0x5b53382d, 0x77abaca3, 0x09e3d36c,
                0xa0a4e1d4, 0xa275d9c7, 0xa280bc4b, 0xa50d1b76, 0x0a5f84cb, 0xa86cd5bd, 0xb3f427ba, 0xc6fc4cd0,
                0xc73c19b9, 0xd905d85f, 0xd919f9ad, 0xda3fc312, 0xdc4ca5b9, 0xe38ef05b, 0xedce8e57, 0xf68ad23e,
                0xfb3b9c59,
                // dnsseed.bluematt.me
                0x1061d85f, 0x2d5325b0, 0x3505ef91, 0x4c42b14c, 0x623cce72, 0x067e4428, 0x6b47e32e, 0x6e47e32e,
                0x87aed35f, 0x96fe3234, 0xac81419f, 0xb6f9bb25, 0xc9ddb4b9, 0xcbd8aca3, 0xd55c09b0, 0xd5640618,
                0xdaa9144e, 0xdfb99088, 0xe0339650, 0xeb8221af, 0xfcbfd75b,
                // dnsseed.bitcoin.dashjr.org
                0x028ea62e, 0x2cf968be, 0x2d9cf023, 0x3bedb812, 0x40373745, 0x40aa9850, 0x42504a28, 0x50b8f655,
                0x5a86e548, 0x6d79f459, 0x70681b41, 0x74a8cf1f, 0x779233d4, 0x8b2380b2, 0x9dcc342f, 0xa331b5ad,
                0xa95b4c90, 0xb05ff750, 0x0bfde3d4, 0x0c15c136, 0xd3912552, 0xd56ce69d, 0xd8af5454, 0xfce48068,
                // seed.bitcoinstats.com
                0x10c23a35, 0x1168b223, 0x11ae871f, 0x14ddce34, 0x018ce3d5, 0x1b242934, 0x20bcf754, 0x33954d33,
                0x355609b0, 0x39fd202f, 0x4df35e2f, 0x4f23f22b, 0x5707f862, 0x8602bdce, 0x8e09703e, 0x90009ede,
                0x9ffb125b, 0xa33c4c90, 0xa9c4ec57, 0xaa2d5097, 0xae52fb94, 0x00ed2636, 0xedf5649f, 0x0f41a6bc,
                0xfe03cf22,
                // seed.bitcoin.jonasschnelli.ch
                0x23159dd8, 0x368fea55, 0x50bd4031, 0x5395de6c, 0x05c6902f, 0x60c09350, 0x66d6d168, 0x70d90337,
                0x7a549ac3, 0x9012d552, 0x94a60f33, 0xa490ff36, 0xb030d552, 0xb0729450, 0xb12b4c4a, 0x0b7e7e60,
                0xc4f84b2f, 0xc533f42f, 0xc8f60ec2, 0xc9d1bab9, 0xd329cb74, 0xe4b26ab4, 0xe70e5db0, 0xec072034,
                // seed.btc.petertodd.org
                0x10ac1242, 0x131c4a79, 0x1477da47, 0x2899ec63, 0x45660451, 0x4b1b0050, 0x6931d0c2, 0x070ed85f,
                0x806a9950, 0x80b0d522, 0x810d2bc1, 0x829d3b8b, 0x848bdfb0, 0x87a5e52e, 0x9664bb25, 0xa021a6df,
                0x0a5f8548, 0x0a66c752, 0xaaf5b64f, 0xabba464a, 0xc5df4165, 0xe8c5efd5, 0xfa08d01f,
                // seed.bitcoin.sprovoost.nl
                0x14420418, 0x1efdd990, 0x32ded23a, 0x364e1e54, 0x3981d262, 0x39ae6ed3, 0x5143a699, 0x68f861cb,
                0x6f229e23, 0x6fe45d8e, 0x77db09b0, 0x7a1cd85f, 0x8dd03b8b, 0x92aec9c3, 0xa2debb23, 0xa47dee50,
                0xb3566bb4, 0xcb1845b9, 0xcd51c253, 0xd541574d, 0xe0cba936, 0xfb2c26d0,
                // dnsseed.emzy.de
                0x16e0d7b9, 0x1719c2b9, 0x1edfd04a, 0x287eff2d, 0x28f54e3e, 0x3574c1bc, 0x36f1b4cf, 0x3932571b,
                0x3d6f9bbc, 0x4458aa3a, 0x4dd2cf52, 0x05483e97, 0x559caed5, 0x59496251, 0x66d432c6, 0x7501f7c7,
                0x7775599f, 0x8e0ea28b, 0x8f3d0d9d, 0x902695de, 0xa6ada27b, 0xbb00875b, 0xbc26c979, 0xd1a2c58a,
                0xf6d33b8b, 0xf9d95947,
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
