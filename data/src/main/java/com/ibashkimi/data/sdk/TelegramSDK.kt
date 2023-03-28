package com.ibashkimi.data.sdk

import android.util.Log
import com.ibashkimi.data.utils.getName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import org.drinkless.td.libcore.telegram.Client
import org.drinkless.td.libcore.telegram.TdApi

private const val TAG = "TelegramSDK"

/**
 * The TelegramSDK class provides the ability to send requests to the Telegram API.
 */
class TelegramSDK: Client.ResultHandler {

    /**
     * The Client object is used to send requests to the Telegram API.
     */
    private val client : Client = createTelegramClient()

    /**
     * Sends a request to the Telegram API and returns the result as a Flow.
     * @param query The request to send to the Telegram API.
     * @return A Flow with the result of the request as an object of class T.
     */
    suspend inline fun <reified T : TdApi.Object> send(query: TdApi.Function): Flow<T> = withContext(Dispatchers.IO) {
        sendAsFlow(query, TdApi.Ok::class.java).filterIsInstance()
    }

    /**
     * Sends a request to the Telegram API and returns the result as a Flow.
     * @param query The request to be sent to the Telegram API.
     * @param resultType The type of object to be obtained in response to the request.
     * @return A Flow with the result of the request in the form of an object of class T.
     */
    suspend inline fun <reified T : TdApi.Object> send(query: TdApi.Function, resultType: Class<T>): Flow<T> = withContext(Dispatchers.IO) {
        sendAsFlow(query, resultType).filterIsInstance()
    }

    /**
     * Returns the path to the loaded file as a Flow.
     * If the file is already loaded, its local path is returned.
     * If the file is not yet loaded, it is first loaded, and then its local path is returned.
     * @param downloadableFile TdApi.File object that needs to be downloaded.
     * @return Flow with a string value of the local path of the downloaded file.
     */
    fun getDownloadableFilePath(downloadableFile: TdApi.File): Flow<String> {
        if (isFileDownloaded(downloadableFile)) return flowOf(downloadableFile.local.path)
        val fileId = downloadableFile.id
        return downloadFile(fileId).map { downloadableFile.local.path }
    }

    /**
     * Checks if a file is uploaded.
     * @param file TdApi.File object to check.
     * @return true if the file is uploaded, false otherwise.
     */
    fun isFileDownloaded(file: TdApi.File): Boolean = file.local?.isDownloadingCompleted == false

    /**
     * Downloads a file with the specified ID.
     * @param fileId The ID of the file to download.
     * @return A Flow that emits a signal when the file has been successfully downloaded.
     */
    fun downloadFile(fileId: Int): Flow<Unit> = sendAsFlow(TdApi.DownloadFile(fileId, 1, 0, 0, true), TdApi.Ok::class.java).map { }

    /**
     * Sends a request to TDLib and emits the result as a Flow.
     * @param query The request to send.
     * @return A Flow that emits the result of the request.
     */
    fun <T: TdApi.Object> sendAsFlow(query: TdApi.Function, resultType: Class<T>): Flow<TdApi.Object> = callbackFlow {
        client.send(query) { result ->
            when {
                resultType.isInstance(result) -> trySend(result).isSuccess
                result is TdApi.Error -> error(query, "Error code: ${result.code}. Error message: ${result.message}")
                else -> error(query, "Unknown server response result type ${result.getName()}")
            }
        }
        awaitClose()
    }

    /**
     * Closes the connection with the client.
     */
    fun close() = client.close()

    /**
     * Function used for logging errors that takes a request object and an error message string as parameters.
     * @param query Request object containing information about the executed request.
     * @param message String containing additional information about the error.
     */
    private fun CoroutineScope.error(query: TdApi.Object, message:String) {
        val title = "Error when trying to request ${query.getName()}"
        error(title, message)
    }


    /**
     * A function used for logging errors that takes in an error message header and an error message string.
     * @param title A string containing the header of the error message.
     * @param message A string containing additional information about the error (optional parameter).
     */
    private fun CoroutineScope.error(title:String, message : String = "") {
        val errorMessage = "$title. $message"
        Log.e(TAG, errorMessage)
        cancel(errorMessage, Exception(message))
    }


    /**
     * A new client is created in the constructor and the logging level is set (1).
     */
    private fun createTelegramClient() : Client {
        return Client.create(this, null, null).apply {
            send(TdApi.SetLogVerbosityLevel(1), this@TelegramSDK)
        }
    }

    override fun onResult(data : TdApi.Object) {
        Log.d(TAG, "onResult: $data")
    }
}