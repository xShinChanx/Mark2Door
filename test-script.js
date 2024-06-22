import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  vus: 10, // Number of virtual users
  duration: '30s', // Duration of the test
};

export default function () {
  const url = 'https://spring-security-service-jl4ebnk3lq-ez.a.run.app/auth/signin';
  const payload = JSON.stringify({
    email: 'adel@gmail.com',
    password: 'yeet',
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  let response = http.post(url, payload, params);

  // Check if the request was successful
  check(response, {
    'status is 200': (r) => r.status === 200,
  });

  // Pause for 1 second between iterations
  sleep(1);
}
