package com.openai.springGpt.apiUtil;

import com.openai.springGpt.dto.completion.CompletionRequest;
import com.openai.springGpt.dto.completion.CompletionResult;
import com.openai.springGpt.dto.completion.chat.ChatCompletionRequest;
import com.openai.springGpt.dto.completion.chat.ChatCompletionResult;
import com.openai.springGpt.dto.edit.EditRequest;
import com.openai.springGpt.dto.edit.EditResult;
import com.openai.springGpt.dto.embedding.EmbeddingRequest;
import com.openai.springGpt.dto.embedding.EmbeddingResult;
import com.openai.springGpt.dto.engine.Engine;
import com.openai.springGpt.dto.file.File;
import com.openai.springGpt.dto.finetune.FineTuneEvent;
import com.openai.springGpt.dto.finetune.FineTuneRequest;
import com.openai.springGpt.dto.finetune.FineTuneResult;
import com.openai.springGpt.dto.image.CreateImageEditRequest;
import com.openai.springGpt.dto.image.CreateImageRequest;
import com.openai.springGpt.dto.image.ImageResult;
import com.openai.springGpt.dto.model.Model;
import com.openai.springGpt.dto.moderation.ModerationRequest;
import com.openai.springGpt.dto.moderation.ModerationResult;
import com.openai.springGpt.dto.response.DeleteResult;
import com.openai.springGpt.dto.response.OpenAiResponse;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;

public interface OpenAiApi {

    @GET("v1/models")
    Single<OpenAiResponse<Model>> listModels();

    @GET("/v1/models/{model_id}")
    Single<Model> getModel(@Path("model_id") String modelId);

    @POST("/v1/completions")
    Single<CompletionResult> createCompletion(@Body CompletionRequest request);
    
    @POST("/v1/chat/completions")
    Single<ChatCompletionResult> createChatCompletion(@Body ChatCompletionRequest request);

    @Deprecated
    @POST("/v1/engines/{engine_id}/completions")
    Single<CompletionResult> createCompletion(@Path("engine_id") String engineId, @Body CompletionRequest request);

    @POST("/v1/edits")
    Single<EditResult> createEdit(@Body EditRequest request);

    @Deprecated
    @POST("/v1/engines/{engine_id}/edits")
    Single<EditResult> createEdit(@Path("engine_id") String engineId, @Body EditRequest request);

    @POST("/v1/embeddings")
    Single<EmbeddingResult> createEmbeddings(@Body EmbeddingRequest request);

    @Deprecated
    @POST("/v1/engines/{engine_id}/embeddings")
    Single<EmbeddingResult> createEmbeddings(@Path("engine_id") String engineId, @Body EmbeddingRequest request);

    @GET("/v1/files")
    Single<OpenAiResponse<File>> listFiles();

    @Multipart
    @POST("/v1/files")
    Single<File> uploadFile(@Part("purpose") RequestBody purpose, @Part MultipartBody.Part file);

    @DELETE("/v1/files/{file_id}")
    Single<DeleteResult> deleteFile(@Path("file_id") String fileId);

    @GET("/v1/files/{file_id}")
    Single<File> retrieveFile(@Path("file_id") String fileId);

    @POST("/v1/fine-tunes")
    Single<FineTuneResult> createFineTune(@Body FineTuneRequest request);

    @POST("/v1/completions")
    Single<CompletionResult> createFineTuneCompletion(@Body CompletionRequest request);

    @GET("/v1/fine-tunes")
    Single<OpenAiResponse<FineTuneResult>> listFineTunes();

    @GET("/v1/fine-tunes/{fine_tune_id}")
    Single<FineTuneResult> retrieveFineTune(@Path("fine_tune_id") String fineTuneId);

    @POST("/v1/fine-tunes/{fine_tune_id}/cancel")
    Single<FineTuneResult> cancelFineTune(@Path("fine_tune_id") String fineTuneId);

    @GET("/v1/fine-tunes/{fine_tune_id}/events")
    Single<OpenAiResponse<FineTuneEvent>> listFineTuneEvents(@Path("fine_tune_id") String fineTuneId);

    @DELETE("/v1/models/{fine_tune_id}")
    Single<DeleteResult> deleteFineTune(@Path("fine_tune_id") String fineTuneId);

    @POST("/v1/images/generations")
    Single<ImageResult> createImage(@Body CreateImageRequest request);

    @POST("/v1/images/edits")
    Single<ImageResult> createImageEdit(@Body RequestBody requestBody);

    @POST("/v1/images/variations")
    Single<ImageResult> createImageVariation(@Body RequestBody requestBody);

    @POST("/v1/moderations")
    Single<ModerationResult> createModeration(@Body ModerationRequest request);

    @Deprecated
    @GET("v1/engines")
    Single<OpenAiResponse<Engine>> getEngines();

    @Deprecated
    @GET("/v1/engines/{engine_id}")
    Single<Engine> getEngine(@Path("engine_id") String engineId);
}
