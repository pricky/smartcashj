package cc.smartcash.smartcashj.wallet;

import cc.smartcash.smartcashj.crypto.HDPath;
import cc.smartcash.smartcashj.script.Script;

/** Defines a structure for hierarchical deterministic wallets. */
public interface KeyChainGroupStructure {
    /** Map desired output script type to an account path */
    HDPath accountPathFor(Script.ScriptType outputScriptType);

    /** Default {@link KeyChainGroupStructure} implementation. Based on BIP32 "Wallet structure". */
    public static final KeyChainGroupStructure DEFAULT = new KeyChainGroupStructure() {
        @Override
        public HDPath accountPathFor(Script.ScriptType outputScriptType) {
            if (outputScriptType == null || outputScriptType == Script.ScriptType.P2PKH)
                return DeterministicKeyChain.ACCOUNT_ZERO_PATH;
            else if (outputScriptType == Script.ScriptType.P2WPKH)
                return DeterministicKeyChain.ACCOUNT_ONE_PATH;
            else
                throw new IllegalArgumentException(outputScriptType.toString());
        }
    };
}
