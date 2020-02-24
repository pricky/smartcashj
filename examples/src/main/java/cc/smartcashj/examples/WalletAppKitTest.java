package cc.smartcashj.examples;

import cc.smartcash.smartcashj.core.ECKey;
import cc.smartcash.smartcashj.core.NetworkParameters;
import cc.smartcash.smartcashj.kits.WalletAppKit;
import cc.smartcash.smartcashj.params.MainNetParams;
import cc.smartcash.smartcashj.utils.BriefLogFormatter;

import java.io.File;

public class WalletAppKitTest {

    public static void main(String[] args) {

        NetworkParameters params;
        params = MainNetParams.get();
        // Start up a basic app using a class that automates some boilerplate. Ensure we always have at least one key.
        WalletAppKit kit = new WalletAppKit(params, new File("."), "smartcash2") {
            @Override
            protected void onSetupCompleted() {
                // This is called in a background thread after startAndWait is called, as setting up various objects
                // can do disk and network IO that may cause UI jank/stuttering in wallet apps if it were to be done
                // on the main thread.
                if (wallet().getKeyChainGroupSize() < 1)
                    wallet().importKey(new ECKey());
            }
        };


            // Regression test mode is designed for testing and development only, so there's no public network for it.
            // If you pick this mode, you're expected to be running a local "bitcoind -regtest" instance.
            kit.connectToLocalHost();


// Download the block chain and wait until it's done.
        kit.startAsync();
        kit.awaitRunning();
    }
}
