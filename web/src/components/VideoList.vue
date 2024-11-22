<template>
  <div class="video-list space-y-8 px-4 py-6">
    <!-- 添加分类选择和上传按钮 -->
    <div class="flex justify-between mb-6">
      <div class="category-filter">
        <el-radio-group v-model="selectedCategory" @change="handleCategoryChange">
          <el-radio-button :label="null">全部</el-radio-button>
          <el-radio-button
              v-for="category in categorys"
              :key="category.id"
              :label="category.id"
          >
            {{ category.name }}
          </el-radio-button>
        </el-radio-group>
      </div>
      <el-button icon="Plus" type="primary" @click="showUploadDialog">
        添加视频
      </el-button>
    </div>

    <!-- 显示视频列表，每行五个视频 -->
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-6">
      <el-card
          v-for="video in videos"
          :key="video.id"
          class="video-card rounded-lg shadow-lg hover:shadow-2xl transition-shadow duration-300"
      >
        <div class="card-header">
          <img v-if="video.thumbnailUrl!=null" :src="video.thumbnailUrl" alt="Thumbnail"
               class="video-thumbnail rounded-lg"/>
          <img v-else alt="Thumbnail" class="video-thumbnail rounded-lg"
               src="public/favicon.ico"/>
        </div>
        <div class="card-body p-4">
          <h3 class="video-title text-lg font-semibold mb-2">{{ video.title }}</h3>
          <p class="video-desc text-sm text-gray-600 mb-4">{{ video.desc }}</p>
          <el-button
              class="w-full"
              size="mini"
              type="primary"
              @click="playVideo(video)"
          >
            ▶ 播放视频
          </el-button>
        </div>
      </el-card>
    </div>

    <!-- 添加分页组件 -->
    <div class="pagination-container flex justify-center mt-6">
      <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 30, 50]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>

    <!-- 弹出层视频播放器 -->
    <el-dialog v-model="dialogVisible" :before-close="closeVideoAction" center draggable width="80%">
      <template #title>
        <div class="dialog-title text-center text-2xl font-semibold">
          {{ currentVideo.title || "播放视频" }}
        </div>
      </template>
      <div class="video-container relative">
        <video
            ref="videoPlayer"
            :src="currentVideo.videoUrl"
            class="video-player rounded-lg"
            controls
            @ended="dialogVisible = false"
            @pause="isPlaying = false"
            @play="isPlaying = true"
        ></video>
        <div v-if="!isPlaying"
             class="play-button-overlay absolute inset-0 flex items-center justify-center bg-black bg-opacity-30 cursor-pointer"
             @click="startPlaying">
          <el-icon class="text-white text-6xl">
            <VideoPlay/>
          </el-icon>
        </div>
      </div>
      <template #footer>
        <el-button class="w-full" @click="dialogVisible = false">关闭</el-button>
      </template>
    </el-dialog>

    <!-- 加载提示 -->
    <div v-if="loading" class="loading flex justify-center mt-12">
      <el-loading/>
    </div>

    <!-- 无数据提示 -->
    <div v-if="!videos.length && !loading" class="no-data text-center mt-12">
      <el-empty description="暂无视频数据"/>
    </div>

    <!-- 添加视频表单对话框 -->
    <el-dialog
        v-model="uploadDialogVisible"
        :before-close="handleUploadDialogClose"
        title="添加视频"
        width="50%"
    >
      <el-form
          ref="uploadForm"
          :model="videoForm"
          :rules="rules"
          label-width="100px"
      >
        <el-form-item label="视频标题" prop="title">
          <el-input v-model="videoForm.title" placeholder="请输入视频标题"/>
        </el-form-item>

        <el-form-item label="视频描述" prop="desc">
          <el-input
              v-model="videoForm.desc"
              placeholder="请输入视频描述"
              type="textarea"
          />
        </el-form-item>

        <el-form-item label="分类" prop="categoryId">
          <el-select v-model="videoForm.categoryId" placeholder="请选择分类">
            <el-option :value="1" label="默认分类"/>
            <!-- 这里可以根据实际需求添加更多分类 -->
          </el-select>
        </el-form-item>

        <el-form-item label="视频文件" prop="videoFile">
          <el-upload
              :auto-upload="false"
              :on-change="handleVideoChange"
              accept="video/*"
              class="video-uploader"
          >
            <el-button type="primary">选择视频文件</el-button>
            <template #tip>
              <div class="el-upload__tip">支持mp4、mov等视频格式</div>
            </template>
          </el-upload>
        </el-form-item>

        <el-form-item label="缩略图" prop="thumbnailFile">
          <el-upload
              :auto-upload="false"
              :on-change="handleThumbnailChange"
              :show-file-list="false"
              accept="image/*"
              class="thumbnail-uploader"
          >
            <img v-if="thumbnailUrl" :src="thumbnailUrl" class="thumbnail"/>
            <el-icon v-else class="thumbnail-uploader-icon">
              <Plus/>
            </el-icon>
          </el-upload>
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="uploadDialogVisible = false">取消</el-button>
          <el-button :loading="submitting" type="primary" @click="submitVideo">
            确认添加
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {addVideo, getVideoPage} from "@/api/videoService";
import {getCategorys} from "@/api/categoryService";
import {uploadFile} from "@/api/fileService";
import {Plus, VideoPlay} from '@element-plus/icons-vue' // 导入图标组件

export default {
  components: {
    VideoPlay,  // 注册图标组件
    Plus,
  },
  data() {
    return {
      videos: [], // 视频列表
      categorys: [], // 分类列表
      loading: true, // 加载状态
      dialogVisible: false, // 弹出层显示状态
      currentVideo: {}, // 当前播放的视频信息
      isPlaying: false, // 新增：视频播放状态
      uploadDialogVisible: false,
      submitting: false,
      videoForm: {
        title: '',
        desc: '',
        categoryId: 1,
        videoUrl: '',
        thumbnailUrl: '',
        state: 1,
        userId: 1, // 这里需要根据实际登录用户设置
      },
      videoFile: null,
      thumbnailFile: null,
      thumbnailUrl: '',
      rules: {
        title: [{required: true, message: '请输入视频标题', trigger: 'blur'}],
        desc: [{required: true, message: '请输入视频描述', trigger: 'blur'}],
        categoryId: [{required: true, message: '请选择分类', trigger: 'change'}],
      },
      selectedCategory: null, // 添加选中的分类ID表示全部
      currentPage: 1,
      pageSize: 10,
      total: 0,
    };
  },
  async mounted() {
    await this.fetchVideos(); // 页面加载时获取视频数据
    await this.fetchCategorys(); // 页面加载时获取视频数据
  },
  methods: {
    // 修改获取视频数据方法
    async fetchVideos() {
      try {
        const params = {
          currentPage: this.currentPage,
          pageSize: this.pageSize
        };
        if (this.selectedCategory) {
          params.categoryId = this.selectedCategory;
        }
        const response = await getVideoPage(params);
        if (response.code === 200) {
          this.videos = response.data.records;
          this.total = response.data.total;
        } else {
          this.$message.error(response.msg || "加载失败");
        }
      } catch (error) {
        this.$message.error("无法加载数据");
      } finally {
        this.loading = false;
      }
    },

    // 获取分类数据
    async fetchCategorys() {
      try {
        const response = await getCategorys();
        if (response.code === 200) {
          this.categorys = response.data; // 设置视频列表
        } else {
          this.$message.error(response.msg || "加载失败");
        }
      } catch (error) {
        this.$message.error("无法加载数据");
      } finally {
        this.loading = false; // 加载完成
      }
    },

    // 开始播放视频
    startPlaying() {
      const videoPlayer = this.$refs.videoPlayer;
      if (videoPlayer) {
        videoPlayer.play();
        this.isPlaying = true;
      }
    },

    // 打开弹窗
    playVideo(video) {
      this.currentVideo = video;
      this.dialogVisible = true;
      this.isPlaying = false; // 重置播放状态
    },

    // 修改：关闭弹窗方法
    closeVideoAction(done) {
      const videoPlayer = this.$refs.videoPlayer;
      if (videoPlayer) {
        videoPlayer.pause();
        this.isPlaying = false; // 重置播放状态
      }
      done();
    },

    showUploadDialog() {
      this.uploadDialogVisible = true;
    },

    handleVideoChange(file) {
      this.videoFile = file.raw;
    },

    handleThumbnailChange(file) {
      this.thumbnailFile = file.raw;
      this.thumbnailUrl = URL.createObjectURL(file.raw);
    },

    async submitVideo() {
      try {
        await this.$refs.uploadForm.validate();

        this.submitting = true;

        // 上传视频文件
        if (this.videoFile) {
          const videoResult = await uploadFile(this.videoFile);
          this.videoForm.videoUrl = videoResult.data; // 假设接口返回文件URL
        }

        // 上传缩略图
        if (this.thumbnailFile) {
          const thumbnailResult = await uploadFile(this.thumbnailFile);
          this.videoForm.thumbnailUrl = thumbnailResult.data;
        }

        // 提交视频信息
        const result = await addVideo(this.videoForm);
        if (result.code === 200) {
          this.$message.success('视频添加成功');
          this.uploadDialogVisible = false;
          this.fetchVideos(); // 刷新视频列表
        }
      } catch (error) {
        this.$message.error(error.message || '添加失败');
      } finally {
        this.submitting = false;
      }
    },

    handleUploadDialogClose() {
      this.$refs.uploadForm.resetFields();
      this.videoFile = null;
      this.thumbnailFile = null;
      this.thumbnailUrl = '';
      this.uploadDialogVisible = false;
    },

    // 修改分类切换处理方法
    async handleCategoryChange(categoryId) {
      this.loading = true;
      this.currentPage = 1; // 切换分类时重置页码
      try {
        const params = {
          currentPage: this.currentPage,
          pageSize: this.pageSize
        };
        if (categoryId) {
          params.categoryId = categoryId;
        }
        const response = await getVideoPage(params);
        if (response.code === 200) {
          this.videos = response.data.records;
          this.total = response.data.total;
        } else {
          this.$message.error(response.msg || "加载失败");
        }
      } catch (error) {
        this.$message.error("无法加载数据");
      } finally {
        this.loading = false;
      }
    },

    // 处理页码改变
    handleCurrentChange(newPage) {
      this.currentPage = newPage;
      this.fetchVideos();
    },

    // 处理每页条数改变
    handleSizeChange(newSize) {
      this.pageSize = newSize;
      this.currentPage = 1; // 重置到第一页
      this.fetchVideos();
    },
  },
};
</script>

<style scoped>
.video-thumbnail {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.video-title {
  font-size: 1.125rem;
}

.video-desc {
  font-size: 0.875rem;
  color: #4a4a4a;
}

.video-player {
  width: 100%;
  height: 500px;
}

.play-button-overlay {
  transition: all 0.3s ease;
}

.play-button-overlay:hover {
  background-opacity: 0.5;
}

.thumbnail-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.thumbnail-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.thumbnail-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
  line-height: 178px;
}

.thumbnail {
  width: 178px;
  height: 178px;
  display: block;
  object-fit: cover;
}

/* 添加分页样式 */
.pagination-container {
  margin-top: 20px;
  padding: 10px 0;
}
</style>
