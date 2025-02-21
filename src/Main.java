import java.util.Scanner;

public class Main {
    private static int [][] walshTable;
    private static int [] CDMA_sequence;

    public static void computeCDMA(int [] data, int N){

        for(int i=0; i<N; i++){
            CDMA_sequence[i] = 0;
        }
        for(int i=0; i<N; i++)
            for(int j= 0; j< N; j++){
                CDMA_sequence[j] += walshTable[i][j] * data[i];
            }

        System.out.println("CDMA_SEQUENCE: ");
        for(int i=0; i<N; i++)
            System.out.print(CDMA_sequence[i] + " ");
        System.out.println(" ");
    }

    public static void main(String [] args){

        // get input station data
        Scanner scaner = new Scanner(System.in);
        int[] cmdaCode = {
                3540, 12, 166, 222, -262, -18, -116, -336, -172, 152, 402, -58, -122,
                -26, 60, -228, -610, -282, -148, 132, -260, 208, 10, -194, 370, -162,
                100, -160, -420, 132, 22, -386, 16, 44, -178, -206, -286, -142, 120,
                16, 284, 204, -146, 70, 266, -226, -36, 248, 70, -382, 584, -36, -216,
                120, 226, 170, -386, -26, 268, 316, -52, -216, -86, 78
        };

        System.out.print("Input n: ");
        int n = Integer.valueOf(scaner.nextLine().trim());
        int N = (int) Math.pow(2, n);
        walshTable = generateWalshMatrix(N);
        // inialization
        CDMA_sequence = new int[N];
        //add what there is from cmdaCode to CMDA_sequence
        for(int i = 0; i < cmdaCode.length; i++)
            CDMA_sequence[i] = cmdaCode[i];

        System.out.println("walshTable: ");
        //computeCDMA(data, N);

        do{
            System.out.println("Input station number: ");
            int K = scaner.nextInt();
            if(K <= 0 || K > N){
                System.out.println("out of range");
                break;
            }
            int d = 0;
            for(int i = 0; i< N; i++ ){
                d += walshTable[K-1][i]* CDMA_sequence[i];
            }

            System.out.println("Station " + K + " data is: " + (d/N));

        }while (true);

    }

    public static int[][] generateWalshMatrix(int size) {
        int[][] walshMatrix = new int[size][size];

        // Start with H1
        walshMatrix[0][0] = 1;

        // Iteratively construct higher order matrices
        for (int k = 1; k < size; k *= 2) {
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    // Top-left quadrant (copy of Hk)
                    walshMatrix[i][j] = walshMatrix[i][j];
                    // Top-right quadrant (copy of Hk)
                    walshMatrix[i][j + k] = walshMatrix[i][j];
                    // Bottom-left quadrant (copy of Hk)
                    walshMatrix[i + k][j] = walshMatrix[i][j];
                    // Bottom-right quadrant (negated Hk)
                    walshMatrix[i + k][j + k] = -walshMatrix[i][j];
                }
            }
        }

        return walshMatrix;
        //bounce
    }

}
