import javax.print.attribute.standard.JobStateReason

import postAndInternals.*

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

    fun findPostById (id: Int): Post? {
        for (post in posts) {
            if (post.id == id)
                return post
        }
        return null
    }

    fun update(post: Post): Boolean {
        var answer = false
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
                answer = true
            }
        return answer
    }

    fun cleanWallservice() {
        posts = emptyArray()
        id = 1
    }

    fun reporting (report: Report) {

        fun findCommentById (id: Int): Comment? {
            for (comment in comments) {
                if (comment.id == id)
                    return comment
            }
            return null
        }

        if ((findCommentById(report.commentId) != null) && report.reason in 1..8)
            reports += report.copy()
        else
            throw RuntimeException("Указанный пост не существует или причина жалобы некорректна")
    }

}