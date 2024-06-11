import React, { useEffect, useState } from 'react';

const TestPage = () => {
    const [message, setMessage] = useState<string>('');

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch('http://localhost:8085/cart/test'); // Added 'http://' protocol
                if (response.ok) {
                    const data = await response.text();
                    setMessage(data);
                } else {
                    setMessage('Error fetching data');
                }
            } catch (error) {
                console.error('Error fetching data:', error);
                setMessage('Error fetching data');
            }
        };

        fetchData();
    }, []);

    return (
        <div>
            <h1>Test Page</h1>
            <p>Message from backend: {message}</p>
        </div>
    );
};

export default TestPage;
