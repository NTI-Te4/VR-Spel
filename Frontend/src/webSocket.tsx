import { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";

export type MessageEntry = {
  username: string;
  score: number;
  uploaded: string;
};

export function useWebSocketData(): MessageEntry[] {
  const [message, setMessages] = useState<MessageEntry[]>([]);

  const client = new Client({
    brokerURL: "ws://172.238.242.254:8001/gs-guide-websocket",
    reconnectDelay: 5000,
  });

  useEffect(() => {
    client.onConnect = () => {
      console.log("Connected!");
        
      // Subcription to the topic
      client.subscribe("/topic/scores", (msg) => {
        if (!msg.body) return;
        
        const data: MessageEntry[] = JSON.parse(msg.body);
        setMessages(data);

      });
      client.publish({ destination: "/app/hello" });
    };

    client.activate();

    // WS cleanup 
    return () => { client.deactivate(); }
  }, []);

  return message;
}