package com.jacky.android.opengl.shaderUtil;

import com.jacky.android.JackyActivity;
import com.jacky.engine.resource.TextFileUtile;

import java.io.InputStreamReader;

import static android.opengl.GLES20.*;

/**
 * Created by Administrator on 2016/3/9.
 */
public class ShaderBase {

    public  int MVPMatrixHandle;//�ܱ任��������id
    public  int PositionHandle; //����λ����������id
    public  int TexCoorHandle; //��������������������id
    public  int program;

    public void initBase(){
        glUseProgram(program);

        //��ȡ�����ж���λ����������id
        PositionHandle = glGetAttribLocation(program, "point");
        //��ȡ�����ж�������������������id
        TexCoorHandle = glGetAttribLocation(program, "texture");
        //��ȡ�������ܱ任��������id
        MVPMatrixHandle = glGetUniformLocation(program, "mvp_matrix");
    }

    public  int load(String vertPath, String fragPath){
        // These lines of code first take in the file path
        // for both our vertex shader and our fragment shader
        // and then create a string containing all
        // of the source code of both shaders and put them
        // into vert and frag. These will later be passed into
        // our created shader objects in our create() function
        String vert = null;
        String frag = null;
        //
        try {
            vert = TextFileUtile.loadAsString(new InputStreamReader(JackyActivity.assetManager.open(vertPath)));
            frag = TextFileUtile.loadAsString(new InputStreamReader(JackyActivity.assetManager.open(fragPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return create(vert, frag);
    }
    public  int create(String vert, String frag){
        // creates a program object and assigns it to the
        // variable program.
        int program = glCreateProgram();
        // glCreateShader specificies the type of shader
        // that we want created. For the vertex shader
        // we define it as GL_VERTEX_SHADER
        int vertID = glCreateShader(GL_VERTEX_SHADER);
        // Specificies that we want to create a
        // GL_FRAGMENT_SHADER
        int fragID = glCreateShader(GL_FRAGMENT_SHADER);
        // glShaderSource replaces the source code in a shader
        // object.
        // We've defined our vertex shader object and now
        // we want to pass in our vertex shader that we
        // managed to build as a string in our load
        // function.
        //
        glShaderSource(vertID, vert);
        // does the same for our fragment shader
        glShaderSource(fragID, frag);

        // This group of code tries to compile our shader object
        // it then gets the status of that compiled shader and
        // if it proves to be false then it prints an error to
        // the command line.
        glCompileShader(vertID);
        int [] compiled=new int[1];
        glGetShaderiv(vertID, GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0)
        {//������ʧ������ʾ������־��ɾ����shader
            System.err.print("���붥����ɫ��ʧ��!");
            System.err.print("OPENGL ES������Ϣ:" + glGetShaderInfoLog(vertID));
            glDeleteShader(vertID);
            vertID = 0;
        }


        // This group of code tries to compile our shader object
        // it then gets the status of that compiled shader and
        // if it proves to be false then it prints an error to
        // the command line.
        glCompileShader(fragID);
        glGetShaderiv(fragID, GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0)
        {//������ʧ������ʾ������־��ɾ����shader
            System.err.print("����ƬԪ��ɫ��ʧ��!");
            System.err.print("OPENGL ES������Ϣ:" + glGetShaderInfoLog(fragID));
            glDeleteShader(fragID);
            fragID = 0;
        }

        // This attaches our vertex and fragment shaders
        // to the program object that we defined at the
        // start of this tutorial.
        glAttachShader(program, vertID);
        glAttachShader(program, fragID);
        // this links our program object
        glLinkProgram(program);
        //
        glValidateProgram(program);

        // this then returns our created program
        // object.
        return program;
    }
}
