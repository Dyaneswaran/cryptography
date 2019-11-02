# Cryptography
This repository supplies a basic implementation of the standard cryptogrpahic methods used to secure communications.

Programming Langauge - Java

## Ciphers - 
> - Playfair
> - RailFence
> - Hill

## Public Key Cryptography - 
> - RSA 
> - Diffie Hellman Key Exchange

## Hashing Algorithms - 
> - MD5
> - SHA

## Encryption - 
> - DES

### **NOTE**

- A simple and a not-the-best working code to calculate the cofactors of a NxN matrix is provided in Matrix.java.

- Miller Rabin Primality checking is also implemented in the Diffie-Hellman implementation. It needs to be done 'n' times which is not yet done. 


### TO-DO 

1. Integrate calculation of NxN Determinant in Matrix.java in HillCipher.java.
2. Make DES, MD5, SHA account for the empty string as well.
3. Extend RSA to implement RFC 3447. (Atleast the datatype conversions).
4. Miller Rabin Primality testing to be completed.


## References - 

1. Check out all the wikipedia documentations of the relevent topics.
2. Use this [link](https://cse.unl.edu/~ssamal/crypto/genhash.php) to validate your intermediate outputs in MD5, SHA algorithms.


Thank you, guys. Enjoy.
