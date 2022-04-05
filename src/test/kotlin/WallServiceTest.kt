import org.junit.Test
import org.junit.Assert.*
import postAndInternals.*

class WallServiceTest {

    private val service = WallService

    @Test
    fun updateSuccessful() {
        service.add(Post(text = "первый пост"))
        service.add(Post(text = "второй пост"))
        service.add(Post(text = "третий пост"))
        val result = service.update(Post(id = 1, text = "обновленный пост"))
        assertTrue(result)
    }

    @Test
    fun updateNotSuccessful() {
        service.cleanWallservice()
        service.add(Post(text = "первый пост"))
        service.add(Post(text = "второй пост"))
        service.add(Post(text = "третий пост"))
        val result = service.update(Post(id = 4, text = "обновленный пост"))
        assertFalse(result)
    }

    @Test
    fun addIdNotNull() {
        service.cleanWallservice()
        val post = Post(text = "новый пост")
        val result = service.add(post)
        assertNotEquals(result.id, post.id)
    }

    @Test(expected = PostNotFoundException::class)
    fun commentShouldThrow() {
        service.cleanWallservice()
        service.add(Post(text = "первый пост"))
        service.createComment(Comment(postId = 2, text = "комментарий"))
    }

    @Test
    fun commentShouldNotThrow() {
        service.cleanWallservice()
        service.add(Post(text = "первый пост"))
        service.createComment(Comment(postId = 1, text = "комментарий"))
    }

    @Test (expected = RuntimeException::class)
    fun reportIdShouldThrow() {
        service.cleanWallservice()
        service.add(Post(text = "первый пост"))
        service.createComment(Comment(postId = 1, text = "комментарий"))
        service.reporting(Report(commentId = 2, reason = 1))
    }

    @Test (expected = RuntimeException::class)
    fun reportReasonShouldThrow() {
        service.cleanWallservice()
        service.add(Post(text = "первый пост"))
        service.createComment(Comment(postId = 1, text = "комментарий"))
        service.reporting(Report(commentId = 1, reason = 9))
    }

    @Test
    fun reportShouldNotThrow() {
        service.cleanWallservice()
        service.add(Post(text = "первый пост"))
        service.createComment(Comment(postId = 1, text = "комментарий"))
        service.reporting(Report(commentId = 1, reason = 1))
    }

}