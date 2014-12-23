public class Solution {
	public static void main(String arg[]){
		Solution s = new Solution();
		int A[] = {1,1};
		int B[] = {1,2};
		System.out.print(s.findMedianSortedArrays(A,B));
	}
    
	public double findMedianSortedArrays(int A[], int B[]) {
        return findMedianSortedArrays(A, 0, A.length-1, B, 0, B.length-1);
    }
    
    private double findMedianSortedArrays(int A[], int Abegin, int Aend, int B[], int Bbegin, int Bend){
        // edge case
        if(Aend < Abegin && Bend < Bbegin){return 0;}
        if(Aend < Abegin){return (Bend-Bbegin+1)%2==0?(B[(Bend+Bbegin)/2]+B[(Bbegin+Bend)/2+1])/2.0:B[(Bend+Bbegin)/2];}
        if(Bend < Bbegin){return (Aend-Abegin+1)%2==0?(A[(Aend+Abegin)/2]+A[(Abegin+Aend)/2+1])/2.0:A[(Aend+Abegin)/2];}
        if(Aend == Abegin){
            if((Bend-Bbegin+1)%2==0){
                return medianOfThree(A[Abegin], B[(Bbegin+Bend)/2], B[(Bbegin+Bend)/2+1]);
            }else{
                return (A[Abegin]+B[(Bbegin+Bend)/2])/2.0;
            }
        }
        if(Bend == Bbegin){
            if((Aend-Abegin+1)%2==0){
                return medianOfThree(B[Bbegin], A[(Abegin+Aend)/2], A[(Abegin+Aend)/2+1]);
            }else{
                return (B[Bbegin]+A[(Abegin+Aend)/2])/2.0;
            }
        }
        
        // recursive call, classify by odd/even
        double Amedian, Bmedian;
        int cut;
        if((Aend-Abegin+1)%2==0){
            if((Bend-Bbegin+1)%2==0){
                Amedian = (A[(Aend+Abegin)/2]+A[(Aend+Abegin)/2+1])/2.0;
                Bmedian = (B[(Bend+Bbegin)/2]+B[(Bend+Bbegin)/2+1])/2.0;
                cut = Math.min(Bend - (Bbegin+Bend)/2, (Abegin+Aend)/2-Abegin+1);
                return Amedian < Bmedian?findMedianSortedArrays(A, Abegin+cut, Aend, B, Bbegin, Bend-cut):findMedianSortedArrays(A, Abegin, Aend-cut, B, Bbegin+cut, Bend);
            }else{
                Amedian = (A[(Aend+Abegin)/2]+A[(Aend+Abegin)/2+1])/2.0;
                Bmedian = B[(Bbegin+Bend)/2];
                cut = Math.min(Bend - (Bbegin+Bend)/2, (Abegin+Aend)/2-Abegin+1);
                return Amedian < Bmedian?findMedianSortedArrays(A, Abegin+cut, Aend, B, Bbegin, Bend-cut):findMedianSortedArrays(A, Abegin, Aend-cut, B, Bbegin+cut, Bend);
            }
        }else{
            if((Bend-Bbegin+1)%2==0){
                Amedian = A[(Abegin+Aend)/2];
                Bmedian = (B[(Bend+Bbegin)/2]+B[(Bend+Bbegin)/2+1])/2.0;
                cut = Math.min(Bend - (Bbegin+Bend)/2, (Abegin+Aend)/2-Abegin+1);
                return Amedian < Bmedian?findMedianSortedArrays(A, Abegin+cut, Aend, B, Bbegin, Bend-cut):findMedianSortedArrays(A, Abegin, Aend-cut, B, Bbegin+cut, Bend);
            }else{
                Amedian = A[(Abegin+Aend)/2];
                Bmedian = B[(Bbegin+Bend)/2];
                cut = Math.min(Bend - (Bbegin+Bend)/2, (Abegin+Aend)/2-Abegin+1);
                return Amedian < Bmedian?findMedianSortedArrays(A, Abegin+cut, Aend, B, Bbegin, Bend-cut):findMedianSortedArrays(A, Abegin, Aend-cut, B, Bbegin+cut, Bend);
            }
        }
    }
    
    private int medianOfThree(int a, int b, int c){
        if(a > b){
            return b > c?b:a>c?c:a;
        }else{
            return a > c?a:b>c?c:b;
        }
    }
}