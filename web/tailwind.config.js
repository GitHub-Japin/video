module.exports = {
    mode: 'jit',
    content: [
        './index.html',  // 如果使用 HTML 文件
        './src/**/*.{vue,js,ts,jsx,tsx}', // 确保 Tailwind 处理 Vue 文件
    ],
    theme: {
        extend: {},
    },
    plugins: [],
}
