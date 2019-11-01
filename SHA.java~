import java.math.BigInteger;

public class SHA{
	static String hexToBin(String hex){
		String bin = "";
		for(int i = 0; i < hex.length(); ++i)
			switch(hex.charAt(i)){
				case '0': bin += "0000"; break;
				case '1': bin += "0001"; break;
				case '2': bin += "0010"; break;
				case '3': bin += "0011"; break;
				case '4': bin += "0100"; break;
				case '5': bin += "0101"; break;
				case '6': bin += "0110"; break;
				case '7': bin += "0111"; break;
				case '8': bin += "1000"; break;
				case '9': bin += "1001"; break;
				case 'a': case 'A': bin += "1010"; break;
				case 'b': case 'B': bin += "1011"; break;
				case 'c': case 'C': bin += "1100"; break;
				case 'd': case 'D': bin += "1101"; break;
				case 'e': case 'E': bin += "1110"; break;
				case 'f': case 'F': bin += "1111"; break;
			}
		return bin;
	}

	static BigInteger rotateLeft(BigInteger a, int s, int n){
		BigInteger topbits = a.shiftRight(n - s);
		BigInteger mask = BigInteger.ONE.shiftLeft(n).subtract(BigInteger.ONE);
		return a.shiftLeft(s).or(topbits).and(mask);	
	}

	public static void main(String[] args){
		BigInteger BI2 = BigInteger.valueOf(2), BI2P32 = BI2.pow(32), BI2P64 = BI2.pow(64);

		String message = "Betty has bought some butter";
		String binstring = hexToBin(String.format("%x", new BigInteger(message.getBytes())));
		BigInteger length = new BigInteger(String.format("%s", binstring.length())).mod(BI2P64);

		int padlength = 448 - length.intValue()%512 + (length.intValue()%512 < 448 ? 0: 512);
		binstring += "1" + new String(new char[padlength - 1]).replace('\0', '0');
		
		String lengthstring = hexToBin(length.toString(16));
		binstring += new String(new char[64 - lengthstring.length()]).replace('\0', '0') + lengthstring;
		
		BigInteger a0 = new BigInteger("67452301", 16), b0 = new BigInteger("EFCDAB89", 16),
			   c0 = new BigInteger("98BADCFE", 16), d0 = new BigInteger("10325476", 16),
			   e0 = new BigInteger("C3D2E1F0", 16);
		
		for(int i = 0; i < binstring.length(); i += 512){
			String block = binstring.substring(i, i + 512);
			BigInteger[] words = new BigInteger[80];
			for(int j = 0; j < 512; j += 32)
				words[j >> 5] = new BigInteger(block.substring(j, j + 32), 2);
			for(int j = 16; j < 80; ++j)
				words[j] = rotateLeft(words[j-3].xor(words[j-8]).xor(words[j-14]).xor(words[j-16]), 1, 32);
			BigInteger a = a0, b = b0, c = c0, d = d0, e = e0;

			for(int j = 0; j < 80; ++j){
				BigInteger f = BigInteger.ZERO, k = f;
				switch(j/20){
					case 0:
						f = (b.and(c)).or(b.not().and(d));
						k = new BigInteger("5A827999", 16);
						break;
					case 1:
						f = b.xor(c).xor(d);
						k = new BigInteger("6ED9EBA1", 16);
						break;
					case 2:
						f = (b.and(c)).or(c.and(d)).or(d.and(b));
						k = new BigInteger("8F1BBCDC", 16);
						break;
					case 3:
						f = b.xor(c).xor(d);
						k = new BigInteger("CA62C1D6", 16);
						break;
				}
				f = f.add(rotateLeft(a, 5, 32)).add(e).add(k).add(words[j]).mod(BI2P32);
				e = d;
				d = c;
				c = rotateLeft(b, 30, 32);
				b = a;
				a = f;
			}
			a0 = a0.add(a).mod(BI2P32);
			b0 = b0.add(b).mod(BI2P32);
			c0 = c0.add(c).mod(BI2P32);
			d0 = d0.add(d).mod(BI2P32);
			e0 = e0.add(e).mod(BI2P32);
		}
		System.out.print("\n" + a0.toString(16) + b0.toString(16) + c0.toString(16) + d0.toString(16) + e0.toString(16));
	}
}
