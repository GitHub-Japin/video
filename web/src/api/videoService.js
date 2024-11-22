import axios from "axios";

const apiBase = "http://localhost:8081/v1/video"; // 后端接口地址

// 获取视频列表
export const getVideos = async () => {
    try {
        const response = await axios.get(`${apiBase}/all`);
        return response.data; // 返回后端的 JSON 数据
    } catch (error) {
        console.error("获取视频列表失败:", error);
        throw new Error("无法获取视频数据");
    }
};

// 添加视频
export const addVideo = async (videoData) => {
    try {
        const response = await axios.post(`${apiBase}`, videoData);
        return response.data;
    } catch (error) {
        console.error("添加视频失败:", error);
        throw new Error("无法添加视频");
    }
};
