package matrixPkg;

import Jama.SingularValueDecomposition;
import Utils.Pair;

public class Jama_Matrix implements Matrix {
	
	Jama.Matrix M;
	
	public Jama_Matrix(Jama.Matrix MM){
		this.M=MM;
	}
	
	public Jama_Matrix(double[][] array){
		this.M= new Jama.Matrix(array);
	}

	@Override
	public Pair<Matrix, Matrix> getSVD() {
		SingularValueDecomposition svd = this.M.svd();
		Matrix u = (Matrix) svd.getU();
		Matrix v = (Matrix) svd.getV();
		Pair<Matrix, Matrix> uv = new Pair<Matrix, Matrix>(u,v);
		return uv;
	}
	
	@Override
	public double get(int i, int j){
		return M.get(i,  j);
	}
	
}
