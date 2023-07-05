
function generateKeyPair() {
    const EthCrypto = require('eth-crypto');
    const identity = EthCrypto.createIdentity();
    return {
        privateKey: identity.privateKey,
        publicKey: identity.publicKey,
        address: identity.address
    };
}