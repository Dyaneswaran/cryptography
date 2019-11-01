public class HillCipher{

	static int determinant(int[][] M){
		return M[0][0] * (M[1][1] * M[2][2] - M[1][2] * M[2][1])
		     - M[0][1] * (M[1][0] * M[2][2] - M[1][2] * M[2][0])
		     + M[0][2] * (M[1][0] * M[2][1] - M[1][1] * M[2][0]);
	}

	static int[][] adjugate(int[][] M){
		int[][] adj = new int[3][3];
		adj[0][0] = (M[1][1] * M[2][2] - M[1][2] * M[2][1]);
		adj[1][0] = -(M[1][0] * M[2][2] - M[1][2] * M[2][0]);
		adj[2][0] = (M[1][0] * M[2][1] - M[1][1] * M[2][0]);
		adj[0][1] = -(M[0][1] * M[2][2] - M[0][2] * M[2][1]);
		adj[1][1] = (M[0][0] * M[2][2] - M[0][2] * M[2][0]);
		adj[2][1] = -(M[0][0] * M[2][1] - M[0][1] * M[2][0]);
		adj[0][2] = (M[0][1] * M[1][2] - M[0][2] * M[1][1]);
		adj[1][2] = -(M[0][0] * M[1][2] - M[0][2] * M[1][0]);
		adj[2][2] = (M[0][0] * M[1][1] - M[0][1] * M[1][0]);
		return adj;	
	}

	static int mod(int m, int n){
		if(m < 0)
			while(m < 0)
				m += n;
		return m%n;	
	}

	static int modInverse(int m, int n){
		m = mod(m, n);
		for(int i = 1; i <= n; ++i)
			if((i*m)%n == 1)
				return i;
		return -1;	
	}
	
	static int[][] inverse(int[][] M){
		//determinant and adjugate.
		int det = determinant(M);
		int[][] adj = adjugate(M);

		//modular inverses.
		int detinverse = modInverse(det, 26);
		for(int i = 0; i < 9; ++i)
			adj[i/3][i%3] = mod(detinverse * adj[i/3][i%3], 26);
		return adj;
	}
	
	static int[][] multiply(int[][] A, int[][] B){
		int[][] C = new int[A.length][B[0].length];
		for(int i = 0; i < A.length; ++i)
			for(int j = 0; j < B[0].length; ++j){
				C[i][j] = 0;
				for(int k = 0; k < A[0].length; ++k)
					C[i][j] += A[i][k] * B[k][j];
			}
		return C;
	}

	public static void main(String[] args){
		int[][] K = { {17,17,5}, {21,18,21}, {2,2,19} };
		int[][] KI = inverse(K);

		String message = "paymoremoney";
		String ciphertext = encrypt(message, K);
		String decipheredtext = encrypt(ciphertext, KI);
		System.out.print("\nCiphertext: " + ciphertext);
		System.out.print("\nDeciphertext: " + decipheredtext);
	}
	
	static String encrypt(String plaintext, int[][] K){
		//convert plaintext to row vectors.
		int rows = plaintext.length()/3, cols = 3;
		int[][] P = new int[rows][cols];
		
		for(int i = 0; i < plaintext.length(); ++i)
			P[i/3][i%3] = mod(plaintext.charAt(i) - 'a', 26);
			
		//encrypt plaintext.
		int[][] C = multiply(P, K);

		//convert ciphertext to string.
		String ciphertext = "";
		for(int i = 0; i < C.length * C[0].length; ++i)
			ciphertext += (char) ('a' + mod(C[i/3][i%3], 26));
		return ciphertext;
	}
}
