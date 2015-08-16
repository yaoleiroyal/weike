package app.controllers;

import app.kits.GridFsKit;
import com.github.sog.controller.BasicController;
import com.google.common.base.Strings;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.Restful;
import com.jfinal.render.RenderException;
import com.mongodb.gridfs.GridFSDBFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>
 * .
 * </p>
 *
 * @author Jerry Ou
 * @version 1.0 2014-07-16 17:04
 * @since JDK 1.6
 */
@Before(Restful.class)
public class AttachmentController extends BasicController {
    public void index(){
        renderNull();
    }

    public void show() {
        String id = getPara();
        if(Strings.isNullOrEmpty(id)){
            renderNull();
            return;
        }
        GridFSDBFile file = GridFsKit.getFileWithName(id);

        HttpServletResponse response = getResponse();
        response.setContentType(file.getContentType());
        response.setContentLength((int) file.getLength());
        try {
            OutputStream out = response.getOutputStream();
            BufferedInputStream is = new BufferedInputStream(file.getInputStream());
            byte[] bytes = new byte[(int) file.getLength()];
            int len;
            while((len = is.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            out.flush();
            renderNull();
        } catch (IOException e) {
            throw new RenderException(e);
        }

    }

}
