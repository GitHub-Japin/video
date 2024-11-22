import axios from "axios";

const apiBase = "http://localhost:8081/v1/file/uploadFile"; // 后端接口地址

// 上传文件
export const uploadFile = async (file) => {
    const formData = new FormData();
    formData.append('file', file);
    try {
        const response = await axios.post(`${apiBase}`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
        return response.data;
    } catch (error) {
        console.error("文件上传失败:", error);
        throw new Error("文件上传失败");
    }
};
