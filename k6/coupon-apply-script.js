import http from 'k6/http';
import {check} from 'k6';

export const options = {
    vus: 5000,        // 5000명의 동시 사용자
    duration: '30s',  // 30초 동안 지속 (더 긴 시간으로 부하 유지)
};

export default function () {
    // 매번 새로운 랜덤 사용자 ID 생성 (중복되지 않도록)
    const timestamp = Date.now();
    const random = Math.floor(Math.random() * 1000000);
    const userId = timestamp + random + __VU * 1000000;

    const payload = JSON.stringify({
        userId: userId,
    });

    const headers = {
        'Content-Type': 'application/json',
    };

    const response = http.post(
        'http://localhost:8080/coupon/apply',
        payload,
        {headers}
    );

    check(response, {
        'is OK': (response) => response.status === 200,
        'response time under 1s': (response) => response.timings.duration < 1000,
    });

}