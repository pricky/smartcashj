package cc.smartcashj.examples;

import cc.smartcash.smartcashj.core.NetworkParameters;
import cc.smartcash.smartcashj.params.MainNetParams;
import cc.smartcash.smartcashj.script.Script;
import cc.smartcash.smartcashj.wallet.Wallet;

public class NewAddress {

    public static void main(String[] args) throws Exception {
        NetworkParameters params = MainNetParams.get();
        Wallet wallet = Wallet.createDeterministic(params, Script.ScriptType.P2PKH);
        System.out.println("send money to: " + wallet.freshReceiveAddress().toString());
    }
}
