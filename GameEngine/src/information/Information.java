package information;

public class Information {
	
	/*	This is entirely theory stuff, do not use this.
	 * 
	 * ----- VAO ----
	 * - VERTEX ARRAY OBJECT
	 * - Stores data in slots, called Attribute Lists
	 * - Used for storing different data, e.g. vertex positions
	 * 										 vertex colours
	 * 										 normal vectors
	 * 										 text co-ords
	 * - Stores the data sets as Vertex Buffer Objects, or VBOs
	 * 
	 * ----- VBO ----
	 * - VERTEX BUFFER OBJECT
	 * - Just an array of numbers that represents data.
	 * - Has a unique id for accessing it
	 * --------------
	 * 
	 * Models are rendered as triangles.
	 * Data stores each three vertices of the triangle
	 * 
	 * ---- COORDINATE SYSTEM ----
	 * 			1|y
	 * 			 |
	 * 			 |
	 * -1		 |(0,0)		1
	 * ----------+-----------
	 * 			 |			x
	 * 			 |
	 * 			 |
	 * 		   -1|
	 * z axis is coming towards you from the centre point
	 * 
	 * - Vertices Coordinates must be in counter clockwise
	 * 
	 * ---- SHADERS ----
	 * - Vertex shader - executed once for every vertex
	 * - Fragment shader - executed for every pixel
	 * 
	 * --- Original Shader flowchart ---
	 * Model data in VAO --> Vertex Shader -->
	 * Per vertex variables --> fragment shader --> pixel colour
	 * 
	 * - Instead, use uniform variables.
	 * - Uniform variables can be changed by java in the code at 
	 * any time
	 * 
	 * 
	 */
	
}
