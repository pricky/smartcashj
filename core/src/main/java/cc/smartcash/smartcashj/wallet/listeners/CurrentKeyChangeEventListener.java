package cc.smartcash.smartcashj.wallet.listeners;

import cc.smartcash.smartcashj.wallet.KeyChainGroup;

public interface CurrentKeyChangeEventListener {
    /**
     * Called by {@link KeyChainGroup} whenever a current key and/or address changes.
     */
    void onCurrentKeyChanged();
}
