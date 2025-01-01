// document.addEventListener('DOMContentLoaded', () => {
//     const likeContainers = document.querySelectorAll('.like-container');
//
//     likeContainers.forEach(container => {
//         const likeButton = container.querySelector('.like');
//         const unlikeButton = container.querySelector('.unlike');
//         const boardId = container.dataset.boardId;
//
//         likeButton.addEventListener('click', () => {
//             handleLike(boardId, container, true);
//         });
//
//         unlikeButton.addEventListener('click', () => {
//             handleLike(boardId, container, false);
//         });
//     });
//
//     function handleLike(boardId, container, isLike) {
//         const url = isLike ? `/board/like/${boardId}` : `/board/unlike/${boardId}`;
//         const xhr = new XMLHttpRequest();
//
//         xhr.onreadystatechange = () => {
//             if (xhr.readyState !== XMLHttpRequest.DONE) return;
//
//             if (xhr.status >= 200 && xhr.status < 300) {
//                 const response = xhr.responseText.trim();
//
//                 if (response === '"SUCCESS"') {
//                     alert(isLike ? '좋아요가 반영되었습니다.' : '좋아요가 취소되었습니다.');
//                     updateLikeUI(container, isLike);
//                 } else if (response === '"FAILURE"') {
//                     alert('좋아요 반영에 실패했습니다. 다시 시도해주세요.');
//                 } else if (response === '"NOT_LOGGED_IN"') {
//                     alert('로그인 후 좋아요를 눌러주세요.');
//                 } else {
//                     alert('알 수 없는 오류가 발생했습니다.');
//                 }
//             } else {
//                 alert('서버와의 통신에 실패했습니다.');
//             }
//             console.log('HTTP 상태 코드:', xhr.status);
//         };
//
//         xhr.open('POST', url, true);
//         xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//         xhr.send(`isLike=${isLike}`);
//     }
//
//     function updateLikeUI(container, isLike) {
//         const likeButton = container.querySelector('.like');
//         const unlikeButton = container.querySelector('.unlike');
//         const likeCountSpan = container.querySelector('.like-count');
//
//         let likeCount = parseInt(likeCountSpan.innerText || 0, 10);
//         likeCount = isLike ? likeCount + 1 : likeCount - 1;
//         likeCountSpan.innerText = likeCount;
//
//         likeButton.style.display = isLike ? 'none' : 'inline-block';
//         unlikeButton.style.display = isLike ? 'inline-block' : 'none';
//     }
// });

document.addEventListener('DOMContentLoaded', () => {
    const likeContainers = document.querySelectorAll('.like-container');

    likeContainers.forEach(container => {
        const likeButton = container.querySelector('.like');
        const unlikeButton = container.querySelector('.unlike');
        const boardId = container.dataset.boardId;

        likeButton.addEventListener('click', () => {
            handleLike(boardId, container, true);
        });

        unlikeButton.addEventListener('click', () => {
            handleLike(boardId, container, false);
        });
    });

    function handleLike(boardId, container, isLike) {
        const url = isLike ? `/board/like/${boardId}` : `/board/unlike/${boardId}`;
        const xhr = new XMLHttpRequest();

        xhr.onreadystatechange = () => {
            if (xhr.readyState !== XMLHttpRequest.DONE) return;

            if (xhr.status >= 200 && xhr.status < 300) {
                const response = xhr.responseText.trim();

                if (response === '"SUCCESS"') {
                    alert(isLike ? '좋아요가 반영되었습니다.' : '좋아요가 취소되었습니다.');
                    updateLikeUI(container, isLike);
                } else if (response === '"FAILURE"') {
                    alert('좋아요 반영에 실패했습니다. 다시 시도해주세요.');
                } else if (response === '"NOT_LOGGED_IN"') {
                    alert('로그인 후 좋아요를 눌러주세요.');
                } else {
                    alert('알 수 없는 오류가 발생했습니다.');
                }
            } else {
                alert('서버와의 통신에 실패했습니다.');
            }
            console.log('HTTP 상태 코드:', xhr.status);
        };

        xhr.open('POST', url, true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(`isLike=${isLike}`);
    }

    function updateLikeUI(container, isLike) {
        const likeButton = container.querySelector('.like');
        const unlikeButton = container.querySelector('.unlike');
        const likeCountSpan = container.querySelector('.like-count');

        let likeCount = parseInt(likeCountSpan.innerText || 0, 10);
        likeCount = isLike ? likeCount + 1 : likeCount - 1;
        likeCountSpan.innerText = likeCount;

        likeButton.style.display = isLike ? 'none' : 'inline-block';
        unlikeButton.style.display = isLike ? 'inline-block' : 'none';
    }
});
