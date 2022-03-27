package com.goganesh.bookshop.dto.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.goganesh.bookshop.dto.ChangeBookStatusRequest;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JsonComponent
public class ChangeBookStatusRequestDeserializer extends JsonDeserializer<ChangeBookStatusRequest> {

    @Override
    public ChangeBookStatusRequest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        //todo add exception
        List<String> booksIds = new ArrayList<>();

        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode node = (JsonNode) treeNode.get("booksIds");
        if (node.isArray()) {
            for (JsonNode arrayItem : node) {
                booksIds.add(arrayItem.asText());
            }
        } else {
            booksIds.add(node.asText());
        }

        String status = ((TextNode) treeNode.get("status")).asText();
        return new ChangeBookStatusRequest(booksIds, status);
    }
}
