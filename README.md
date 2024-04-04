# MSCS396-Blowfish
 

Project: Blowfish
MSCS 396: Applied Cryptography
─
Group 2: Members’ name
Lambert, Travis
Trinh, An
Vu, Andrew

Overview
An analysis of the Blowfish Algorithm, which is famously used as a cipher system in different fields. In this paper, we will go through an analysis on the implementation of said algorithm, as well as a report on its general information.

Report:
History (Travis)
  According to Bruce Schneier, the designer of Blowfish, it was created in 1993 as a “drop-in replacement for DES and IDEA.”  It being faster than both makes it an ideal replacement.  According to Geeks for Geeks, “It is one of the first, secure block ciphers not subject to any patents and hence freely available for anyone to use.”  To       this   day 

Algorithm
  Blowfish is an algorithm designed in 1993 as an alternative to DES Algorithm. It follows the Feistel cipher. The algorithm consists of:
  Blocksize: 64 bits
  Key size: 32 bits - 448 bits
  Subkeys (P): 18
  Rounds: 16
  Substitution boxes: 4
  	The structure of the Blowfish algorithm includes:
  Consists of 16 rounds R[i] with i representing the number order of the round.
    In each round, there are four steps:
      XOR the left half of the text with subkey P[i]
      Put the XORed string as input for the F function
      XOR the F function output with the right half of the text
      Switch Left and Right halves
    F function of Blowfish:
      Separates the 32-bit input into four 8-bit chunks S1, S2, S3, S4.
      Add S1 and S2 together mod 2^32 to get S12
      XOR S12 with S3 to get S123
      Add S123 with S4 mod 2^32 to get the final result
(Structure of Blowfish. Source: https://en.wikipedia.org/wiki/Blowfish_(cipher))

Performance & Comparison


Successors


Advantages & Disadvantages (Travis)
One great advantage of blowfish is that it is unpatented and royalty-free with no license required to use.  This means that it is free to use.  A second advantage is that there is no practical attack against the cipher.  Another advantage is that it is much faster than DES and IDEA according to Bruce Schneier.
	According to Bruce Schneier some disadvantages are that it only has a 64 block length and that it was optimized for 32-bit CPUs.
Additional source: What is Blowfish in security? Who uses Blowfish? | Encryption Consulting
Advantages
Faster than other encryption algorithms, such as the Data Encryption Standard (DES)
Blowfish is unpatented and free to use. This means anyone can take and use Blowfish for whatever they want to
The key schedule of Blowfish takes a long time, but this can be advantageous, as brute force attacks are more difficult

Disadvantages
The small block size of Blowfish means that Birthday Attacks can occur and compromise the encryption algorithm
The Blowfish algorithm also has a lesser amount of operations to complete compared to other encryption algorithms
It is followed by Twofish, which was created to replace Blowfish, as it is better in most ways
The key schedule of Blowfish takes a long time, equivalent to encrypting 4KBs of data, which can be a disadvantage or an advantage. On the Disadvantage side, it takes a very long time to do

Vulnerabilities: (Just added Andrew) (Comment (An): Shouldn’t this be in Disadvantages?)

Key Change Impact on Speed
Changing keys in Blowfish can negatively impact speed. This limitation could be a concern when frequent key changes are required.
Lengthy Key Schedule
The key schedule process in Blowfish takes considerable time. This can be a drawback, especially when a quick key setup is essential.
Vulnerability to Brute-force Attacks
Blowfish’s small 64-bit block size makes it susceptible to a class of brute-force attacks. The collision probability (two different inputs producing the same output) increases with the limited block size, potentially compromising security.
Resource-Intensive Key Preprocessing
Introducing a new key in Blowfish requires preprocessing equivalent to 4 KB of text. This preprocessing level, especially for each new key, can impact the speed and efficiency of the algorithm, making it less suitable for certain applications.
Applications (Andrew) - 

References:
For implementation: https://www.geeksforgeeks.org/blowfish-algorithm-with-examples/
For time analysis: https://medium.com/@arie.valiant/java-cryptography-blowfish-encryption-decryption-tutorial-1db5f2cc15f1
https://www.cs.wustl.edu/~jain/cse567-06/ftp/encryption_perf/
For cryptoanalysis:
(TWOFISH) https://www.schneier.com/wp-content/uploads/2016/02/paper-twofish-paper.pdf
History
https://www.schneier.com/academic/blowfish/ (the person who designed blowfish)

