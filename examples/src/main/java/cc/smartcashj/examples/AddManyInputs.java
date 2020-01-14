package cc.smartcashj.examples;

import cc.smartcash.smartcashj.core.NetworkParameters;
import cc.smartcash.smartcashj.core.Utils;
import cc.smartcash.smartcashj.params.MainNetParams;
import cc.smartcash.smartcashj.script.Script;
import cc.smartcash.smartcashj.wallet.DeterministicSeed;
import cc.smartcash.smartcashj.wallet.Wallet;

public class AddManyInputs {


    public static void main(String[] args) {

        NetworkParameters params = MainNetParams.get();
        Wallet wallet = Wallet.createDeterministic(params, Script.ScriptType.P2PKH);

        DeterministicSeed seed = wallet.getKeyChainSeed();
        System.out.println("seed: " + seed.toString());

        System.out.println("creation time: " + seed.getCreationTimeSeconds());
        System.out.println("mnemonicCode: " + Utils.SPACE_JOINER.join(seed.getMnemonicCode()));
    }



}
