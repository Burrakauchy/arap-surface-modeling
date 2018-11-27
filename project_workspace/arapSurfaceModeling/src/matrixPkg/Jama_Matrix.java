package matrixPkg;

import Jama.SingularValueDecomposition;
import Utils.Pair;

public class Jama_Matrix implements Matrix {
	
	Jama.Matrix M;

	@Override
	public Pair<Matrix, Matrix> getSVD() {
		SingularValueDecomposition svd = this.M.svd();
		Matrix u = (Matrix) svd.getU();
		Matrix v = (Matrix) svd.getV();
		Pair<Matrix, Matrix> uv = new Pair<Matrix, Matrix>(u,v);
		return uv;
	}
	
}
