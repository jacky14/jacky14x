package com.jacky.engine.ShaderStrsource;

/**
 * Created by Administrator on 2016/1/3.
 */
public class StrSource {
    public static final String default_vert =
            "precision mediump float;" +
            "attribute vec3 point;" +
            "attribute vec2 texture;" +
                    "uniform mat4 mvp_matrix;" +
                    "varying vec2 shareCoordinate;" +
                    "void main()" +
                    "{gl_Position = mvp_matrix * vec4(point,1) ;" +
                    "shareCoordinate = texture ;}" ;
    public static final String default_frag = "precision mediump float;uniform sampler2D tex0;" +
            "varying vec2 shareCoordinate;" +
            "void main(){" +
            //" vec4 ctmp = texture2D(tex0,shareCoordinate);if(ctmp.a==0){discard;}else{gl_FragColor = texture2D(tex0,shareCoordinate);}"+
            " gl_FragColor = texture2D(tex0,shareCoordinate); " +
            "}";
    public static final String particle_vert = "precision mediump float;"
            +"uniform mat4 mvp_matrix;"
            +"uniform vec3 point_move[100];"
            +"uniform mat4 rotatleM;"
            +"attribute vec3 point;"
            +"attribute vec2 texture;"
            +"attribute float vec_move_idx;"

            +"varying vec2 shareCoordinate;"
            +"void main(){"
            +"  int index = int(vec_move_idx);"
            +"  vec4 movev = vec4(point_move[index],0);"
            +"  gl_Position =  mvp_matrix*(rotatleM * vec4(point,1) + movev); "

            +"  shareCoordinate = texture ;} "
            ;

    public static final String bone_vert = "precision mediump float;\n" +
            "attribute vec3 point;\n" +
            "attribute vec2 texture;\n" +
            "uniform mat4 mvp_matrix;\n" +
            "varying vec2 shareCoordinate;\n" +
            "attribute float verbone_idx;\n" +
            "uniform vec4 u_matrixPalette[135];\n" +
            "vec4 getPosition(){\n" +
            "    int index = int(verbone_idx) * 3;\n" +
            "    vec4 _skinnedPosition;\n" +
            "    vec4 tmpPois = vec4(point, 1.0);\n" +
            "    _skinnedPosition.x = dot(tmpPois, u_matrixPalette[index]);\n" +
            "    _skinnedPosition.y = dot(tmpPois, u_matrixPalette[index + 1]);\n" +
            "    _skinnedPosition.z = dot(tmpPois, u_matrixPalette[index + 2]);\n" +
            "    _skinnedPosition.w = tmpPois.w;\n" +
            "    return _skinnedPosition;}\n" +

            "void main(){\n" +
            "    gl_Position = mvp_matrix * getPosition() ;\n" +
            "    shareCoordinate = texture ;\n" +
            "}";

}
