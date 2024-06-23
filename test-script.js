import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  vus: 500, // Number of virtual users
  duration: '90s', // Duration of the test
  // Additional options can be added for more control, like thresholds and stages
};

export default function () {
  const url = 'https://spring-security-service-jl4ebnk3lq-ez.a.run.app/auth/signin';
  const payload = JSON.stringify({
    email: 'anis@gmail.com',
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
