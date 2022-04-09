import post.*

object WallService {

    private var posts = emptyArray<Post>()
    private var id = 1

    private var comments = emptyArray<Comment>()
    private var commentsId = 1

    private var reports = emptyArray<Report>()

    fun add(post: Post): Post {
        if (posts.isEmpty())
            posts += post.copy(id = id)
        else {
            posts += post.copy(id = posts.last().id + 1)
            id++
        }
        return posts.last()
    }

    fun update(post: Post): Boolean {
        var result = false
        for ((index, savedPost) in posts.withIndex())
            if (savedPost.id == post.id) {
                posts[index] = post.copy(
                    ownerId = savedPost.ownerId,
                    fromId = savedPost.fromId,
                    createdBy = savedPost.createdBy,
                    text = savedPost.text,
                    replyOwnerId = savedPost.replyOwnerId,
                    replyPostId = savedPost.replyPostId,
                    friendsOnly = savedPost.friendsOnly,
                    postType = savedPost.postType,
                    attachments = savedPost.attachments,
                    signerId = savedPost.signerId,
                    canPin = savedPost.canPin,
                    markedAsAds = savedPost.markedAsAds,
                    isFavorite = savedPost.isFavorite,
                    postponedId = savedPost.postponedId,
                )
                result = true
            }
        return result
    }

    fun cleanWallservice() {
        posts = emptyArray()
        id = 1
    }

    fun createComment(comment: Comment) {
        if (findPostById(comment.postId) != null) {
            if (comments.isEmpty())
                comments += comment.copy(id = commentsId)
            else {
                comments += comment.copy(id = comments.last().id + 1)
                commentsId++
            }
        } else
            throw PostNotFoundException("Указанный пост не существует")
    }

    private fun findPostById (id: Int): Post? {
        for (post in posts) {
            if (post.id == id)
                return post
        }
        return null
    }

    fun reporting (report: Report) {
        if ((findCommentById(report.commentId) != null) && report.reason in 1..8)
            reports += report.copy()
        else
            throw PostNotFoundException("Указанный пост не существует или причина жалобы некорректна")
    }

    private fun findCommentById (id: Int): Comment? {
        for (comment in comments) {
            if (comment.id == id)
                return comment
        }
        return null
    }

}