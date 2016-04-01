package com.run.poker.entity.card;

import java.util.Collection;
import java.util.Collections;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class CardList extends HBox {
	
	private boolean hidden = false;
	
	public CardList() {
		//setSpacing(Card.GAP);
		//setPrefSize(Card.HEIGHT, 2 * (Card.WIDTH + Card.GAP));
	}
	
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *         is not supported by this list
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this list
     */
	public void add(Card card) {
		ObservableList<Node> children = getChildren();
		card.setHidden(hidden);
		reset(card);
		children.add(card);
	}
	
	public Card get(int index) {
		ObservableList<Node> children = getChildren();
		return (Card) children.get(index);
	}
	
    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *         is not supported by this list
     */
	public void clear() {
		ObservableList<Node> children = getChildren();
		children.clear();
	}
	
	/**
	 * Resets the layout and transformation properties of the node.
	 * @param node
	 */
	public void reset(Node node) {
		node.setLayoutX(0);
		node.setLayoutY(0);
		node.setTranslateX(0);
		node.setTranslateY(0);
	}
	
	/**
	 * <p> Re-arranges hold cards in each player's hand. 
	 * <p> This is used for visual effects and has no impact on game logics.
	 * <p> Creates a delayed animation which updates the View from one of more changes in the 
	 * Model specified in the EventHandler class.
	 * @param duration desired time for this frame.
	 * @param e The on finish event handler.
	 * @return A Delayed Update Animation
	 */
    public Animation reverseSort() {
		Timeline timeline = new Timeline();
		KeyFrame frame = new KeyFrame(
				Duration.millis(150), e -> {
				ObservableList<Node> workingCollection = FXCollections.observableArrayList(getChildren());
				Collections.sort(workingCollection, Collections.reverseOrder());
				getChildren().setAll(workingCollection);
		});
        timeline.getKeyFrames().add(frame);
        return timeline;
    }
	
	@Override
	public String toString() {
		return getChildren().toString();
	}
}