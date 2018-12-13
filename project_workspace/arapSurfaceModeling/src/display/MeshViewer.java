package display;


import processing.core.*;

import Jcg.geometry.*;
import Jcg.polyhedron.*;
import core.RigidTransformation;

/**
 * A simple 3d viewer for visualizing surface meshes (based on Processing)
 * 
 * @author Luca Castelli Aleardi (INF555, 2012)
 *
 */

public class MeshViewer extends PApplet{
	
	SurfaceMesh mesh; // 3d surface mesh
	int renderType=0; // choice of type of rendering
	int renderModes=2; // number of rendering modes
	RigidTransformation arap;
	
//	String filename="OFF/high_genus.off";
//	String filename="OFF/sphere.off";
//	String filename="OFF/cube.off";
//	String filename="OFF/torus_33.off";
//	String filename="OFF/tore.off";
//	String filename="OFF/tri_hedra.off";
//	String filename="OFF/letter_a.off";
//	String filename="OFF/star.off";
//	String filename="OFF/tri_triceratops.off";
	String filename="OFF/cactus_small.off";
//	String filename="OFF/dino.off";
//	String filename="OFF/square_21_spikes.off";
//	String filename="OFF/cow.off";
	
	public void setup() {
		  size(800,600,P3D);
		  ArcBall arcball = new ArcBall(this);
		  this.mesh=new SurfaceMesh(this, filename);		  
		  arap = new RigidTransformation(mesh.polyhedron3D);;
		  arap.mobilePoints.add(0);
		  arap.fixedPoints.add(50);
		  arap.fixedPoints.add(51);
		  arap.fixedPoints.add(52);
		  arap.fixedPoints.add(53);
	}
		 
		public void draw() {
		  background(0);
		  //this.lights();
		  directionalLight(101, 204, 255, -1, 0, 0);
		  directionalLight(51, 102, 126, 0, -1, 0);
		  directionalLight(51, 102, 126, 0, 0, -1);
		  directionalLight(102, 50, 126, 1, 0, 0);
		  directionalLight(51, 50, 102, 0, 1, 0);
		  directionalLight(51, 50, 102, 0, 0, 1);
		 
		  translate(width/2.f,height/2.f,-1*height/2.f);
		  this.strokeWeight(1);
		  stroke(150,150,150);
		  
		  this.mesh.draw(renderType);
		}
		
		public void zoomIn() {
			this.mesh.zoom=this.mesh.zoom*1.5;
		}
		
		public void zoomOut() {
			this.mesh.zoom=this.mesh.zoom*0.75;
		}
		
		public void incrxPlus(double incr){
			this.mesh.incrx+=incr;
		}
		public void incrxMoins(double incr){
			this.mesh.incrx-=incr;
		}
		public void incryPlus(double incr){
			this.mesh.incry+=incr;
		}
		public void incryMoins(double incr){
			this.mesh.incry-=incr;
		}
		public void incrzPlus(double incr){
			this.mesh.incrz+=incr;
		}
		public void incrzMoins(double incr){
			this.mesh.incrz-=incr;
		}
		public void transform(int loops) {
			Vertex<Point_3> v = arap.polyhedron3D.vertices.get(arap.mobilePoints.get(0));
			Point_3 pi = v.getPoint();
			pi.setX((double)pi.getX() + 10);
			for(int i=0; i < loops; i++) {
				arap.arapIteration();
			}
			arap.updateEverything();
			mesh.polyhedron3D = arap.polyhedron3D;
		}
		
		public void keyPressed(){
			  switch(key) {
			  	case('z'):case('Z'): this.zoomIn(); break;
			    case('x'):case('X'): this.zoomOut(); break;
			    case('i'):this.incrxPlus(0.1); break;
			    case('I'):this.incrxMoins(0.1); break;
			    case('o'):this.incryPlus(0.1); break;
			    case('O'):this.incryMoins(0.1); break;
			    case('p'):this.incrzPlus(0.1); break;
			    case('P'):this.incrzMoins(0.1); break;
//			    case('s'):case('S'): this.subdivide(); break;
			    case('r'):this.renderType=(this.renderType+1)%this.renderModes; break;
			    case('t'):this.transform(1); break;
			  }
		}
		
		
		/**
		 * For running the PApplet as Java application
		 */
		public static void main(String args[]) {
			//PApplet pa=new MeshViewer();
			//pa.setSize(400, 400);
		PApplet.main(new String[] { "MeshViewer" });
		
	}

}
