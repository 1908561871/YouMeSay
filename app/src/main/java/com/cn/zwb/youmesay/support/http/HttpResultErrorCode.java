package com.cn.zwb.youmesay.support.http;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/5/7.
 */
public class HttpResultErrorCode {

   /* ERROR0000	请求的接口不存在
    ERROR0001	访问路由格式有误
    ERROR0002	用户名不能为空
    ERROR0003	用户密码不能为空
    ERROR0004	登录失败
    ERROR0005	未获取到数据
    ERROR0006	必须参数未传递
    ERROR0007	修改失败
    ERROR0008	没有文件被上传
    ERROR0009	文件只有部分被上传
    ERROR0010	上传文件的大小超过了HTML表单中MAX_FILE_SIZE选项指定的值
    ERROR0011	上传的文件超过了php.ini中upload_max_filesize选项限制的值
    ERROR0012	未允许类型
    ERROR0013	文件过大,上传的文件不能超过指定字节
    ERROR0014	上传失败
    ERROR0015	建立存放上传文件目录失败，请重新指定上传目录
    ERROR0016	必须指定上传文件的路径
    ERROR0017	未知错误
    ERROR0018	添加数据失败*/


    public static HashMap<String,String> errorMaps;
    static {
        errorMaps=new HashMap<>();
        errorMaps.put("ERROR0000","请求的接口不存在");
        errorMaps.put("ERROR0001","访问路由格式有误");
        errorMaps.put("ERROR0002","用户名不能为空");
        errorMaps.put("ERROR0003","用户密码不能为空");
        errorMaps.put("ERROR0004","登录失败");
        errorMaps.put("ERROR0005","未获取到数据");
        errorMaps.put("ERROR0006","必须参数未传递");
        errorMaps.put("ERROR0007","修改失败");
        errorMaps.put("ERROR0008","没有文件被上传");
        errorMaps.put("ERROR0009","文件只有部分被上传");
        errorMaps.put("ERROR0010","上传文件的大小超过了HTML表单中MAX_FILE_SIZE选项指定的值");
        errorMaps.put("ERROR0011","上传的文件超过了php.ini中upload_max_filesize选项限制的值");
        errorMaps.put("ERROR0012","未允许类型");
        errorMaps.put("ERROR0013","文件过大,上传的文件不能超过指定字节");
        errorMaps.put("ERROR0014","上传失败");
        errorMaps.put("ERROR0015","建立存放上传文件目录失败，请重新指定上传目录");
        errorMaps.put("ERROR0016","必须指定上传文件的路径");
        errorMaps.put("ERROR0017","未知错误");
        errorMaps.put("ERROR0018","添加数据失败");
    }


    /**
     *
      * @param errorCode 错误码
     * @return
     */
    public static String getErrorString(String errorCode){

        return errorMaps.get(errorCode);

    }


}
