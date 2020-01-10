package cc.smartcashj.examples;

import cc.smartcash.smartcashj.core.Address;
import cc.smartcash.smartcashj.core.DumpedPrivateKey;
import cc.smartcash.smartcashj.core.NetworkParameters;
import cc.smartcash.smartcashj.params.MainNetParams;
import cc.smartcash.smartcashj.script.Script;
import cc.smartcash.smartcashj.wallet.Wallet;

public class NewPK {
    public static void main(String[] args) throws Exception {
        NetworkParameters params = MainNetParams.get();
        Wallet wallet = Wallet.createDeterministic(params, Script.ScriptType.P2PKH);
        Address address = wallet.freshReceiveAddress();
        System.out.println("New Addres: " + address.toString());
        System.out.println("New PK: " + wallet.findKeyFromAddress(address).getPrivateKeyAsWiF(params));
    }
}
