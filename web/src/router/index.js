import {createRouter, createWebHistory} from 'vue-router';
import VideoList from '@/components/VideoList.vue'; // 导入视频列表组件

const routes = [
    {
        path: '/',
        name: 'VideoList',
        component: VideoList,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
