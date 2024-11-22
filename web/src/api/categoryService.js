import axios from "axios";

const apiBase = "http://localhost:8081/v1/category"; // 后端接口地址

// 获取列表
export const getCategorys = async () => {
    try {
        const response = await axios.get(`${apiBase}/all`);
        return response.data; // 返回后端的 JSON 数据
    } catch (error) {
        console.error("获取视频列表失败:", error);
        throw new Error("无法获取视频数据");
    }
};

// 添加
export const addCategory = async (categoryData) => {
    try {
        const response = await axios.post(`${apiBase}`, categoryData);
        return response.data;
    } catch (error) {
        console.error("添加视频失败:", error);
        throw new Error("无法添加视频");
    }
};


// 更新
export const updateCategory = async (categoryData) => {
    try {
        const response = await axios.put(`${apiBase}`, categoryData);
        return response.data;
    } catch (error) {
        console.error("添加视频失败:", error);
        throw new Error("无法添加视频");
    }
};
