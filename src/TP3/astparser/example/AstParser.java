package TP3.astparser.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
 
public class AstParser {
	
	private static int cptClasse = 0;
	private static int cptMethod = 0;
	private static int cptLigne = 0;
	private static int cptPackage = 0;
	private static int cptLigneMethod = 0;
	private static List<String> packages = new ArrayList<>();
	private static Map<Integer,String> classes = new TreeMap<Integer, String>();
 
	//use ASTParse to parse string
	public static void parse(String str) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
 
		cu.accept(new ASTVisitor() {
 
			Set names = new HashSet();
			
			public boolean visit(MethodDeclaration method){
				cptMethod++;
				cptLigneMethod += cu.getLineNumber(method.getLength());
				System.out.println(method.getName());
				return true;
			}
			
			
			public boolean visit(TypeDeclaration declaration){
				countLines(declaration);
				cptClasse++;
				System.out.println(declaration.getName());
				
				MethodDeclaration[] d = declaration.getMethods();
				/*for(int i = 0; i < d.length; i++){
					System.out.println(d[i].getName().toString());
				}*/
				
				classes.put(declaration.getMethods().length, declaration.getName().toString());
				return true;
			}
			
			public boolean visit(PackageDeclaration pack){
				System.out.println(pack.getName().toString());
				if(!packages.contains(pack.getName().toString())){
					cptPackage++;
					packages.add(pack.getName().toString());
				}
				return true;
			}
			
			public void countLines(ASTNode node){
				cptLigne += cu.getLineNumber(node.getLength());
			}
			
		});
 
	}
 
	//read file content into a string
	public static String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
 
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			System.out.println(numRead);
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
 
		reader.close();
 
		return  fileData.toString();	
	}
 
	//loop directory to get file list
	public static void ParseFilesInDir(String path) throws IOException{
		File dirs = new File(".");
		//String dirPath = dirs.getCanonicalPath() + File.separator+"src"+File.separator;
		String dirPath	= path;
		
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles ( );
		String filePath = null;
 
		 for (File f : files ) {
			 filePath = f.getAbsolutePath();
			 if(f.isFile()){
				 parse(readFileToString(filePath));
			 }else{
				 ParseFilesInDir(f.getCanonicalPath());
			 }
		 }
	}
 
	public static void main(String[] args) throws IOException {
		ParseFilesInDir("/auto_home/cbarcelo/Documents/M2-Aigle/workspace neon/workspace/TpAST/src");
		/*String file = readFileToString("/auto_home/cbarcelo/Documents/M2-Aigle/workspace neon/workspace/TpAST/src/tp/Test.java");
		parse(file);*/
		/*String file = readFileToString("/auto_home/cbarcelo/Documents/M2-Aigle/workspace neon/workspace/A/src/tp/Test.java");
		parse(file);*/
		System.out.println("Il y a : "+cptClasse+" Classe(s)");
		System.out.println("Il y a : "+cptMethod+" Méthode(s)");
		System.out.println("Il y a : "+cptPackage+" Package(s)");
		System.out.println("Il y a : "+cptLigne+" Ligne(s)");
		System.out.println("Il y a : "+cptMethod/cptClasse+" Méthode(s) par classe en moyenne");
		System.out.println("Il y a : "+cptLigneMethod/cptMethod+" Ligne(s) par méthode en moyenne");
		//for(int i = 0; i < classes.size(); i++){
			System.out.println(classes.toString());
		//}
	}
}